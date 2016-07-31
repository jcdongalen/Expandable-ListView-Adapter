package ph.com.developer.jc.spywho;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by john_dongalen on 7/20/2016.
 */
public class SpyProgressDialog extends Dialog {

    View view;
    ProgressBar progressBar;

    public SpyProgressDialog(Context context) {
        super(context);
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.progress_dialog, null);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
    }

    @Override
    public void show() {
        super.show();
        setContentView(view);
    }

    public void setProgressBar(int val){
        progressBar.setProgress(val);
    }
}
