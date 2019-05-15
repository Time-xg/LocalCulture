package top.timewl.localculture.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

public class BaseFragment extends Fragment {
    private Activity activity;

    public Context getContext(){
        if (activity == null){
            return MyApplication.getInstance();

        }
        return activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }
}
