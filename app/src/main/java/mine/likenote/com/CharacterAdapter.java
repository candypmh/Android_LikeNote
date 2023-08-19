package mine.likenote.com;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    private ArrayList<CharacterDTO> characterDTOList; //빈 List변수 선언

    //생성자 만들어놓고 비워놨었음^^;; 그래서 에러가 났나..ㅎ
    //1) 이게 이제 characterList를 가져와서 characterDTOList 변수에 복사한거임.
    //원본에 영향을 주면 안되니까 복사본으로 사용하는 것
    public CharacterAdapter(List<CharacterDTO> characterList) {
        this.characterDTOList = new ArrayList<>(characterList);
    }

    public ArrayList<CharacterDTO> getCharacterDTOList() {
        return characterDTOList;
    }

    //2) 어댑터의 데이터 갱신을 위해 사용
    //받아온 값을 characterDTOList 변수에 넣어줌(=데이터 갱신)
    public void setCharacterDTOList(ArrayList<CharacterDTO> characterDTOList) {
        this.characterDTOList = characterDTOList;
        notifyDataSetChanged(); //어댑터 내부 데이터가 변경되었음을 알림
    }




    //생성자를 통해 CharacterList를 받아와서 items에 추가하는 방식으로 변경...
    //뷰 홀더 생성과 뷰 생성 및 연결 작업
    @NonNull
    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_detail,parent,false);
        return new ViewHolder(view);
    }



    // 데이터 바인딩 작업
    // null 체크 2023-8-15
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        CharacterDTO item = characterDTOList.get(position);
        if(item != null){
            holder.bind(item);
        }
    }


    @Override
    public int getItemCount(){
        return characterDTOList.size();
    }




    // 뷰 홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView charNameVH;
        private TextView charSexVH;
        private ImageView charImgVH;
        private TextView charAgeVH;
        private TextView charCmVH;
        private TextView charKgVH;
        private TextView charContentVH;
        private TextView charTagVH;
        private TextView charCreateDateVH;
        // private TextView charLikeChkVH;
        // private TextView charStarVH;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            charNameVH = itemView.findViewById(R.id.detailName);
            charSexVH = itemView.findViewById(R.id.detailSex);
            charImgVH = itemView.findViewById(R.id.detailImg);
            charAgeVH = itemView.findViewById(R.id.detailAge);
            charCmVH = itemView.findViewById(R.id.detailCm);
            charKgVH = itemView.findViewById(R.id.detailKg);
            charContentVH = itemView.findViewById(R.id.detailInfo);
            charTagVH = itemView.findViewById(R.id.detailTag);
            charCreateDateVH = itemView.findViewById(R.id.detailDate);
           // charLikeChkVH = itemView.findViewById(R.id.detailName);
           // charStarVH = itemView.findViewById(R.id.detailName);
        }

        public void bind(CharacterDTO item){
            charNameVH.setText(item.getCharName());
            charSexVH.setText(item.getCharSex());
            charImgVH.setImageResource(item.getCharImg());
            charAgeVH.setText(item.getCharAge());
            charCmVH.setText(item.getCharCm());
            charKgVH.setText(item.getCharKg());
            charContentVH.setText(item.getCharContent());
            charTagVH.setText(item.getCharTag());
            charCreateDateVH.setText(item.getCharCreateDate());

        }
    }




}
