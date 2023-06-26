package mine.likenote.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //아래에서 쓸거라 전역변수 선언
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //어떤 레이아웃을 화면에 보여줄건지

        //버튼(보러가기 기능)
        btn = findViewById(R.id.titleBtn);
        btn.setOnClickListener(new View.OnClickListener() { //빨간줄 Implement 누르면 @Override뜸
            @Override
            public void onClick(View v) { //컴포넌트
                Intent intent = new Intent(getApplicationContext(), Activity_ListFormat.class); //액티비티 - 액티비티간 통신(어디로 이동하라고 알려 줌. 여기서는 Main - Activity_ListFormat)
                startActivity(intent);
            }
        }); //버튼이 클릭되면
    }
}