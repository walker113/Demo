package stay.walker.com.toolbar;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.socks.library.KLog;

import java.security.Permissions;

import stay.walker.com.retrofitdemo.R;
import stay.walker.com.test.Test;

/**
 * @author admin
 */
public class ToolbarActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_toolbar);

        initToolbar();

        Test.parseJson();
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);

        mToolbar.setTitle("Title");
        mToolbar.setSubtitle("subTitle");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent,1000);

            }
        });

        mToolbar.inflateMenu(R.menu.toolbar_menu);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        //业务逻辑

                        Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        startActivityForResult(intent,1000);

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1000){
            if (resultCode==RESULT_OK){
                if (data!=null){
                    Uri uri=data.getData();KLog.e("uri - " + uri);
                    // content://com.android.contacts/data/167
                    // content://com.android.contacts/data/7
                    String[] contact=getPhoneContacts(uri);
                    if (contact!=null){
                        String name=contact[0];//姓名
                        String number=contact[1];//手机号

                        KLog.e(name + " - " + number.replace(" ", ""));
                    }
                }

                getContacts(data.getData());
            }
        }
    }

    /**
     * 读取联系人信息
     * @param uri
     */
    private String[] getPhoneContacts(Uri uri){
        String[] contact = new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null&&cursor.moveToFirst()) {
            //取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);
            contact[1]=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.i("contacts",contact[0]);
            Log.i("contactsUsername",contact[1]);
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

    private void getContacts(Uri data) {
        String[] cols = {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getContentResolver().query(data,
                cols, null, null, null);
        KLog.e("cursor.getCount() - " + cursor.getCount());

        Cursor query = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        KLog.e("query.getCount() - " + query.getCount());
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            // 取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
            int numberFieldColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String name = cursor.getString(nameFieldColumnIndex);
            String number = cursor.getString(numberFieldColumnIndex);
            Toast.makeText(this, name + " " + number, Toast.LENGTH_SHORT).show();
            KLog.e();
        }
    }



}
