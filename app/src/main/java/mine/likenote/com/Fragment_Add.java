package mine.likenote.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Fragment_Add extends Fragment {

    String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    private Context context;
    private static final int SELECT_IMAGE_REQUEST_CODE = 1;
    private String selectedImagePath;
    private Uri uri;

    EditText charName_input;
    ImageView charImg_input;

    //이미지 버튼
    Button selectImgBtn;
    EditText charAge_input;
    EditText charCm_input;
    EditText charKg_input;
    EditText charContent_input;
    EditText charTag_input;
    TextView charCreateDate_input;
    TextView charLikeChk_input;
    RatingBar charStar_input;

    //생성버튼
    Button add_button;
    RadioGroup charSex_input;

    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        charName_input = view.findViewById(R.id.addName);
        charSex_input = view.findViewById(R.id.addSex);
        charImg_input = view.findViewById(R.id.addImage);
        //이미지 생성버튼
        selectImgBtn = view.findViewById(R.id.addSelectImageBtn);
        charAge_input = view.findViewById(R.id.addAge);
        charCm_input = view.findViewById(R.id.addCm);
        charKg_input = view.findViewById(R.id.addKg);
        charContent_input = view.findViewById(R.id.addInfo);
        charTag_input = view.findViewById(R.id.addTag);

        charCreateDate_input = view.findViewById(R.id.addDate);
        charStar_input = view.findViewById(R.id.addRatingBar);
        charLikeChk_input = view.findViewById(R.id.addLikeChk);

        //생성버튼
        add_button = view.findViewById(R.id.addCreateBtn);



        selectImgBtn.setOnClickListener(new View.OnClickListener(){ //이미지 생성 버튼 클릭하면 할 일
        @Override
        public void onClick(View view){
            //이미지 선택 인텐트 생성
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

           //이미지 선택 액티비티 실행
            launcher.launch(intent);

            /*
            //charImg 수정. 사용자가 이미지를 선택하면 launcher를 실행하여 이미지선택하는 앱 호출 => 이미지 선택 버튼 왜만든..?
            charImg_input.setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                launcher.launch(intent);
            });
             */
        }
  });



        add_button.setOnClickListener(new View.OnClickListener() { //생성 버튼 클릭하면 할 일
            @Override
            public void onClick(View view) {

                //charSex 부분(RadioGroup)
                int selectedId = charSex_input.getCheckedRadioButtonId();
                String charSexValue;

                if(selectedId == R.id.addSex_male){
                    charSexValue = "남";

                }else if(selectedId == R.id.addSex_female){
                    charSexValue = "여";
                }else{
                    charSexValue = "무";
                }

                DatabaseHelper dbHelper = new DatabaseHelper(context);
                dbHelper.addCharacter(charName_input.getText().toString().trim(),
                                        charSexValue,//Integer.valueOf(메소드명.getText().toString().trim())인데 int가 없음
                                        selectedImagePath,
                                        charAge_input.getText().toString().trim()+"세",
                                        charCm_input.getText().toString().trim()+ "cm",
                                        charKg_input.getText().toString().trim()+"kg",
                                        charContent_input.getText().toString().trim(),
                                        charTag_input.getText().toString().trim(),
                                        currentDate,
                             false,
                                        0
                                        );
            }
        });

        return view;
    }//onCreate

    

    //charImg 관련 코드. 이미지 선택결과를 처리함. 사용자가 이미지를 선택한 후 선택이미지의 Uri정보를 얻어와 charImg_input.setImageURI(uri)를 통해 이미지 뷰에 표시하고, selectedImagePath를 얻어오는 역할을 함.
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        uri = intent.getData();
                        charImg_input.setImageURI(uri);
                        selectedImagePath = getRealPathFromURI(uri);
                    }
                }
            }
    );



    //Image 절대 경로 구해주는 메서드...URI에서 실제 파일 경로를 얻음
    private String getRealPathFromURI(Uri contentURI){
        String[] projection = {MediaStore.Images.ImageColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentURI, projection, null, null, null);

        if(cursor == null){
            return contentURI.getPath();

        }else{
            cursor.moveToFirst();

            int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA);
            String path = cursor.getString(idx);
            cursor.close();
            return path;
        }
    }


}