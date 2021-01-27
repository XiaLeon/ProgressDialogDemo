import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.memorydemo.R;

public class ProgressDialogDemo extends Activity {

    private Button button;
    private TextView textView;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle onSavedInstance) {
        super.onCreate(onSavedInstance);
        setContentView(R.layout.progress_dialog_demo);

        button = findViewById(R.id.buttonProgressDialog);
        textView = findViewById(R.id.textView6);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 创建并显示进度加载框
                mDialog = ProgressDialog.show(ProgressDialogDemo.this,
                        "Please wait..",
                        "Calculating in the background...",
                        true);

                // 设置文字内容，告诉用户当前正在后台计算
                textView.setText("Calculating in the background...");

                new Thread() {
                    @Override
                    public void run() {
                        try {

                            // 模拟耗时的后台计算
                            sleep(3000);
                        } catch (InterruptedException e) {

                        } finally {

                            // 耗时的后台计算完成，关闭进度框，再次以文字的形式告诉用户
                            mDialog.dismiss();
                            refreshTextView();
                        }
                    }
                }.start();
            }
        });
    }

    private void refreshTextView() {
        textView.post(new Runnable() {
            @Override
            public void run() {

                // 需要在主线程去重新设置文字
                textView.setText("Done with calculating.");
            }
        });
    }
}
