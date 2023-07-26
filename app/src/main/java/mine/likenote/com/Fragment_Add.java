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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Fragment_Add extends Fragment {

    String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

    private Context context;
    private static final int SELECT_IMAGE_REQUEST_CODE = 1;
    private String selectedImagePath="";

    EditText charName_input;
    ImageView charImg_input;
    EditText charAge_input;
    EditText charCm_input;
    EditText charKg_input;
    EditText charContent_input;
    EditText charTag_input;
    EditText charCreateDate_input;
    EditText charLikeChk_input;
    EditText charStar_input;
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
        Button selectImgBtn = view.findViewById(R.id.addSelectImageBtn);
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
        add_button.setOnClickListener(new View.OnClickListener() {
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

                //charImg 부분(ImageView) imageBtn에 대한 클릭 리스너
                /*
                selectImgBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, SELECT_IMAGE_REQUEST_CODE);
                    }
                });
                */

                //charImg 수정
                charImg_input.setOnClickListener(v -> {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    launcher.launch(intent);
                });

                DatabaseHelper dbHelper = new DatabaseHelper(context);
                dbHelper.addCharacter(charName_input.getText().toString().trim(),
                                        charSexValue,
                                        //Integer.valueOf(메소드명.getText().toString().trim())인데 int가 없음
                                        selectedImagePath,
                                        charAge_input.getText().toString().trim(),
                                        charCm_input.getText().toString().trim(),
                                        charKg_input.getText().toString().trim(),
                                        charContent_input.getText().toString().trim(),
                                        charTag_input.getText().toString().trim(),
                                        currentDate,
                             false,
                                        0
                                        //charCreateDate_input.getText().toString().trim(),
                                        //Integer.valueOf(charStar_input.getText().toString().trim())
                                        //charLikeChk_input.getText().toString().trim()
                                        );


            }
        });

        return view;
    }//onCreate

    
    
    //charImg 관련 코드
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        charImg_input.setImageURI(uri);
                        String selectedImagePath = getRealPathFromURI(uri);
                    }
                }
            }
    );




    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null){
            Uri selectedImageUri = data.getData();
            selectedImagePath = getRealPathFromURI(selectedImageUri);
            charImg_input.setImageURI(selectedImageUri);
        }
    }
*/


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