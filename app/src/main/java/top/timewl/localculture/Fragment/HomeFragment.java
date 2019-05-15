package top.timewl.localculture.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import top.timewl.localculture.Adapter.ArticleAdapter;
import top.timewl.localculture.R;
import top.timewl.localculture.Tools.StreamTools;
import top.timewl.localculture.bean.Article;

public class HomeFragment extends BaseFragment {


    private View view;
    private RecyclerView article;
    private List<Article> datas;
    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            parseArticleJSON((String) msg.obj);
            initList();
        }
    };
    private SmartRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_layout,container,false);
        initView();
        postArticle();
        initRefresh();
        return view;

    }

    private void initRefresh() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    private void postArticle() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                String path = "http://chinaqwe.top:8080/homepage/getAllHomeArticle";
                try {
                    url = new URL(path);
                    //获取connection链接对象
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置请求方法和超时时间
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(10000);
                    //添加头信息
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    //connection.setRequestProperty("Content-Length","");

                    int code = connection.getResponseCode();
                    if (code == 200){
                        //获取流的数据，以服务器的形式返回
                        InputStream inputStream = connection.getInputStream();
                        String content = StreamTools.readStream(inputStream);
                        Message message = new Message();
                        message.obj = content;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseArticleJSON(String jsonData) {
        datas = new ArrayList<Article>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length() ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Article article = new Article();
                article.setId(jsonObject.getInt("articleId"));
                int id = jsonObject.getInt("articleId");
                article.setTitle(jsonObject.getString("articleTitle"));
                article.setContent(jsonObject.getString("articleContent"));
                article.setImage(jsonObject.getString("articlePhotoUrl"));
                article.setCreat_time(jsonObject.getString("article_creation_time"));
                datas.add(article);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initList() {

        ArticleAdapter articleAdapter = new ArticleAdapter(getContext(),datas);
        article.setAdapter(articleAdapter);
        article.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        article.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    private void initView() {
        refreshLayout = view.findViewById(R.id.refresh);
        article = view.findViewById(R.id.article_list);
    }
}
