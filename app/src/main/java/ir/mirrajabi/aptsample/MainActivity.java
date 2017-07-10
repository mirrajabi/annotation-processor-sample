package ir.mirrajabi.aptsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestClass testClass = TestClassBuilder.having()
                .activity(this)
                .someField(5)
                .id(654)
                .name("Sample")
                .get();
    }
}
