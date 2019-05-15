package top.timewl.localculture.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import top.timewl.localculture.R;
import top.timewl.localculture.View.SmartImageView;
import top.timewl.localculture.bean.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder>{
    private Context context;
    private List<Article> datas;
    private LayoutInflater mInflater;


    public ArticleAdapter(Context context, List<Article> datas) {
        this.context = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.home_item,null);
        ArticleViewHolder viewHolder = new ArticleViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = datas.get(position);
        holder.image.setImageUrl(article.getImage());
        holder.title.setText(article.getTitle());
        holder.content.setText(article.getContent());
        holder.time.setText(article.getCreat_time());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
class ArticleViewHolder extends RecyclerView.ViewHolder{

    SmartImageView image;
    TextView title;
    TextView content;
    TextView time;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.article_image);
        title = itemView.findViewById(R.id.article_title);
        content = itemView.findViewById(R.id.article_content);
        time = itemView.findViewById(R.id.article_time);
    }
}
