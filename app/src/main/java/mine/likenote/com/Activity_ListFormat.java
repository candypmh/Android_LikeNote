package mine.likenote.com;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_ListFormat extends AppCompatActivity {
    //Bottom Nav 구현 위한 전역변수
    BottomNavigationView bottomNavigationView;

    //Nav연동해서 ListActivity에 얹을 프래그먼트 구현
    FragmentManager fragmentManager;
    //FragmentTransaction fragmentTransaction;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_format); //XML 화면 연결(엑티비티)

        //프래그먼트 구현(액티비티 위에서 프래그먼트 관리)
        fragmentManager = getSupportFragmentManager(); //프래그먼트 관리. 프래그먼트를 추가, 제거, 교체 및 상호작용하는 데 사용
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); //beginTransaction 메서드 통해서 fragmentTransaction 객체 생성하고
                                                                  //이 객체로 프래그먼트를 추가, 제거 또는 교체할 수 있음
        fragmentTransaction.replace(R.id.listFrame, new Fragment_List());  //fragment.java 만들어 줌. 이렇게 new하면 새로고침됨
                                                                           //replace메서드 통해 프래그먼트 바꾸기 가능
        fragmentTransaction.commit(); //저장



        //Bottom Nav관련
        bottomNavigationView = findViewById(R.id.bottomNav); //xml에서 android:id="@+id/bottomNav" 이 부분
        bottomNavigationView.setOnItemSelectedListener(item -> {
            //changeFragment 메서드 밑에서 만들어야 함
            if(item.getItemId() == R.id.listIcon){ //listIcon눌리면 List 프래그먼트로 변경
                changeFragment(new Fragment_List());
            } else if (item.getItemId() == R.id.addIcon) { //addIcon눌리면 List 프래그먼트로 변경
                changeFragment(new Fragment_Add());
            }else{ //likeIcon눌리면 Like 프래그먼트로 변경
                changeFragment(new Fragment_Like());
            }
            return true;
        });
    }



    //changeFragment 메서드 만들기
    private void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); //beginTransaction 메서드 통해서 fragmentTransaction 객체 생성하고
        //이 객체로 프래그먼트를 추가, 제거 또는 교체할 수 있음
        fragmentTransaction.replace(R.id.listFrame, fragment);
        fragmentTransaction.commit();
    }

}//class
