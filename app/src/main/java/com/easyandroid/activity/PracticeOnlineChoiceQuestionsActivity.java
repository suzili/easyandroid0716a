package com.easyandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyandroid.R;

public class PracticeOnlineChoiceQuestionsActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mChoiceA;
    private TextView mAnswerA;
    private LinearLayout mChoiceB;
    private TextView mAnswerB;
    private LinearLayout mChoiceC;
    private TextView mAnswerC;
    private LinearLayout mChoiceD;
    private TextView mAnswerD;
    private LinearLayout mInvisible;
    private Button mChoicebt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_online_choice_questions);
        mChoiceA = (LinearLayout) findViewById(R.id.choice_A);
        mAnswerA = (TextView) findViewById(R.id.answer_A);
        mChoiceB = (LinearLayout) findViewById(R.id.choice_B);
        mAnswerB = (TextView) findViewById(R.id.answer_B);
        mChoiceC = (LinearLayout) findViewById(R.id.choice_C);
        mAnswerC = (TextView) findViewById(R.id.answer_C);
        mChoiceD = (LinearLayout) findViewById(R.id.choice_D);
        mAnswerD = (TextView) findViewById(R.id.answer_D);
        mInvisible = (LinearLayout) findViewById(R.id.invisible);
        mChoicebt = (Button) findViewById(R.id.choice_bt);
        mChoicebt.setOnClickListener(this);
        mChoiceA.setOnClickListener(this);
        mChoiceB.setOnClickListener(this);
        mChoiceC.setOnClickListener(this);
        mChoiceD.setOnClickListener(this);
        initUnchoice();
    }

    private void initUnchoice() {
        mAnswerA.setBackgroundResource(R.drawable.bg_unchoicebutton);
        mAnswerA.setTextColor(getResources().getColor(R.color.colorunchoice));
        mAnswerB.setBackgroundResource(R.drawable.bg_unchoicebutton);
        mAnswerB.setTextColor(getResources().getColor(R.color.colorunchoice));
        mAnswerC.setBackgroundResource(R.drawable.bg_unchoicebutton);
        mAnswerC.setTextColor(getResources().getColor(R.color.colorunchoice));
        mAnswerD.setBackgroundResource(R.drawable.bg_unchoicebutton);
        mAnswerD.setTextColor(getResources().getColor(R.color.colorunchoice));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choice_A:
                initUnchoice();
                mAnswerA.setBackgroundResource(R.drawable.bg_choicebutton);
                mAnswerA.setTextColor(getResources().getColor(R.color.colorchoice));
//                mInvisible.setVisibility(View.VISIBLE);
                break;
            case R.id.choice_B:
                initUnchoice();
                mAnswerB.setBackgroundResource(R.drawable.bg_choicebutton);
                mAnswerB.setTextColor(getResources().getColor(R.color.colorchoice));
//                mInvisible.setVisibility(View.VISIBLE);
                break;
            case R.id.choice_C:
                initUnchoice();
                mAnswerC.setBackgroundResource(R.drawable.bg_choicebutton);
                mAnswerC.setTextColor(getResources().getColor(R.color.colorchoice));
//                mInvisible.setVisibility(View.VISIBLE);
                break;
            case R.id.choice_D:
                initUnchoice();
                mAnswerD.setBackgroundResource(R.drawable.bg_choicebutton);
                mAnswerD.setTextColor(getResources().getColor(R.color.colorchoice));
//                mInvisible.setVisibility(View.VISIBLE);
                break;
            case R.id.choice_bt:
                mInvisible.setVisibility(View.VISIBLE);

        }




    }
}
