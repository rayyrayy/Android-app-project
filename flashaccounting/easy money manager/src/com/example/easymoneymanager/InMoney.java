package com.example.easymoneymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import java.text.SimpleDateFormat;


public class InMoney extends Activity{
	//�ŧi�����ܼ�
	private TextView txtMoneyView,txtNameView;
	private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
	private Button btnAdd,btnMinus,btnMultily,btnDivide,btnEqual,btnClean;
	private Button btnSave;
	private int money1=0;
	private int money2=0;
	private int sumMoney=0;
	private char cmdTmp='n';
    private String btn_num;
	DBAdapter myDb;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inmoney);
        Bundle bundle = getIntent().getExtras();
        btn_num=bundle.getString("btn_num");
		Log.i("inMoney btn_num = ",btn_num);
		
		//元件宣告
		btn1=(Button)findViewById(R.id.Button01);
		btn2=(Button)findViewById(R.id.Button02);
		btn3=(Button)findViewById(R.id.Button03);
		btn4=(Button)findViewById(R.id.Button04);
		btn5=(Button)findViewById(R.id.Button05);
		btn6=(Button)findViewById(R.id.Button06);
		btn7=(Button)findViewById(R.id.Button07);
		btn8=(Button)findViewById(R.id.Button08);
		btn9=(Button)findViewById(R.id.Button09);
		btn0=(Button)findViewById(R.id.Button00);
		btnAdd=(Button)findViewById(R.id.ButtonAdd);
		btnMinus=(Button)findViewById(R.id.ButtonMinus);
		btnMultily=(Button)findViewById(R.id.ButtonMultily);
		btnDivide=(Button)findViewById(R.id.ButtonDivide);
		btnEqual=(Button)findViewById(R.id.ButtonEqual);
		btnClean=(Button)findViewById(R.id.ButtonClean);
		txtMoneyView=(TextView)findViewById(R.id.TextMoneyView);
		txtNameView=(TextView)findViewById(R.id.textNameView);
		btnSave=(Button)findViewById(R.id.ButtonSubmit);
		
		//Button事件定義
		btn1.setOnClickListener(moneyOnClick);
		btn2.setOnClickListener(moneyOnClick);
		btn3.setOnClickListener(moneyOnClick);
		btn4.setOnClickListener(moneyOnClick);
		btn5.setOnClickListener(moneyOnClick);
		btn6.setOnClickListener(moneyOnClick);
		btn7.setOnClickListener(moneyOnClick);
		btn8.setOnClickListener(moneyOnClick);
		btn9.setOnClickListener(moneyOnClick);
		btn0.setOnClickListener(moneyOnClick);
		btnAdd.setOnClickListener(moneyOnClick);
		btnMinus.setOnClickListener(moneyOnClick);
		btnMultily.setOnClickListener(moneyOnClick);
		btnDivide.setOnClickListener(moneyOnClick);
		btnEqual.setOnClickListener(moneyOnClick);
		btnClean.setOnClickListener(moneyOnClick);
		
		btnSave.setOnClickListener(saveOnClick);
		
		
	}
    @Override
    protected void onStart(){
    	super.onStart();
    }
    @Override
    protected void onResume(){
    	super.onResume();
    }
    protected void onRestrart(){
    	super.onRestart();
    }
    @Override
    protected void onPause(){
    	super.onPause();
    	
    }
    @Override
    protected void onStop(){
    	super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    private Button.OnClickListener moneyOnClick = new Button.OnClickListener()
    {
    	public void onClick(View v)
    	{
    		String s=txtMoneyView.getText().toString();
    		if(s=="0")
    		{
    			s="";
    		}
    		switch(v.getId())
    		{
    			
    			case R.id.Button00:
    			{
    				if(txtMoneyView.getText()!="")
    				txtMoneyView.setText(s+"0");
    				break;
    			}
    			case R.id.Button01:
    			{
    				txtMoneyView.setText(s+"1");
    				break;
    			}
    			case R.id.Button02:
    			{
    				txtMoneyView.setText(s+"2");
    				break;
    			}
    			case R.id.Button03:
    			{
    				txtMoneyView.setText(s+"3");
    				break;
    			}
    			case R.id.Button04:
    			{
    				txtMoneyView.setText(s+"4");
    				break;
    			}
    			case R.id.Button05:
    			{
    				txtMoneyView.setText(s+"5");
    				break;
    			}
    			case R.id.Button06:
    			{
    				txtMoneyView.setText(s+"6");
    				break;
    			}
    			case R.id.Button07:
    			{
    				txtMoneyView.setText(s+"7");
    				break;
    			}
    			case R.id.Button08:
    			{
    				txtMoneyView.setText(s+"8");
    				break;
    			}
    			case R.id.Button09:
    			{
    				txtMoneyView.setText(s+"9");
    				break;
    			}
    			case R.id.ButtonAdd:
    			{
    				if(cmdTmp!='n')
    				{
    					calcAnything();    					
    				}
    				else
    				{
    					money1=Integer.parseInt(txtMoneyView.getText().toString());
    					cmdTmp='+';
    					txtMoneyView.setText("");
    				}
    				
    				break;
    			}
    			case R.id.ButtonMinus:
    			{
    				if(cmdTmp!='n')
    				{
    					calcAnything();
    				}
    				else
    				{
    					Log.i("Minus","here");
    					money1=Integer.parseInt(txtMoneyView.getText().toString());
    					cmdTmp='-';
    					txtMoneyView.setText("");
    				}
    				break;
    			}
    			case R.id.ButtonMultily:
    			{
    				if(cmdTmp!='n')
    				{
    					calcAnything();
    				}
    				else
    				{
    					money1=Integer.parseInt(txtMoneyView.getText().toString());
        				txtMoneyView.setText("");
        				cmdTmp='*';
    				}
    				break;
    			}
    			case R.id.ButtonDivide:
    			{
    				if(cmdTmp!='n')
    				{
    					calcAnything();
    				}
    				else
    				{
    					money1=Integer.parseInt(txtMoneyView.getText().toString());
        				cmdTmp='/';
        				txtMoneyView.setText("");
    				}
    				break;
    			}
    			case R.id.ButtonEqual:
    			{
    				if(cmdTmp!='n')
    				{
    					calcAnything();
    				}
    				else
    				{
    					money1=Integer.parseInt(txtMoneyView.getText().toString());
        				txtMoneyView.setText("");
    				}
    				break;
    			}
    			case R.id.ButtonClean:
    			{
    				txtMoneyView.setText("0");
    				money1=0;
    				money2=0;
    				sumMoney=0;
    				cmdTmp='n';
    				break;
    			}
    		}
    	}
    	
    	public void calcAnything()
        {
    		if(money1<=0)
    		{}
    		else
    		{
    			Log.i("calcA","calcB");
            	money2=Integer.parseInt(txtMoneyView.getText().toString());
            	switch(cmdTmp)
            	{
            	case '+':
            		sumMoney=money1+money2;
            		Log.d("sumMoney","Value:"+sumMoney);
            		break;
            	case '-':
            		sumMoney=money1-money2;
            		break;
            	case '*':
        			sumMoney=money1*money2;
            		break;
            	case '/':
            		sumMoney=money1/money2;
            		break;
            		
            	}
            	txtMoneyView.setText(String.valueOf(sumMoney));
            	
    		
    		}
    		cmdTmp='n';
        	
        };
        
    };
    private Button.OnClickListener saveOnClick = new Button.OnClickListener()
    {
    	public void onClick(View v)
    	{
    		//開啟資料庫儲存資料
    		openDB();
    		txtNameView.setText(btn_num);
    		
    		
    		//關閉資料庫
    		closeDB();
    	}
    	
    };
    private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB() {
		myDb.close();
		
	}
    private void insertDataToMemoTable(){
        myDb.insert_MT_Row("List",sumMoney,"Date","Location","Group","Photo",Integer.valueOf(btn_num));
    }
    

}
