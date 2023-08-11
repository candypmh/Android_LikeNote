package mine.likenote.com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Fragment_List extends Fragment {
    RecyclerView recyclerView;
    //2023-07-27 추가
    private List<CharacterDTO> characterDTOList;
    private CharacterAdapter characterAdapter;//어댑터 구현 필요


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        //리싸이클러뷰
        recyclerView = view.findViewById(R.id.recyclerview_list);  //리싸이클러뷰 리스트ID 찾아서
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); //매니저한테 넘김
        characterDTOList = new ArrayList<>(); //리스트 만들고
        // 어댑터 객체 생성
        characterAdapter = new CharacterAdapter(characterDTOList); //리스트 통째로 어댑터에 낌



        //DB에서 캐릭터 정보 가져오기
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        // 데이터베이스에서 데이터 가져오기
        characterDTOList.addAll(dbHelper.getAllCharacters());
        // 어댑터에 데이터 설정
        characterAdapter.setCharacterDTOList((ArrayList<CharacterDTO>) characterDTOList);
        // 리싸이클러뷰에 어댑터 설정
        recyclerView.setAdapter(characterAdapter);

        return view;
    }



}
