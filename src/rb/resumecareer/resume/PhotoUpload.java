package rb.resumecareer.resume;

import java.io.ByteArrayOutputStream;
import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PhotoUpload extends Activity {

	Button btn;
	TextView t;
	ImageView iv;
	Bitmap bimg;
	Bitmap bmp;
	String i1;
	int id, pv;
	byte[] bt;
	DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_upload);
		initalize();

		Intent in = getIntent();
		id = in.getIntExtra("id", 0);

		pv = in.getIntExtra("photovalue", 0);
		final int idclick = in.getIntExtra("idclick", 0);
		final String str = in.getStringExtra("profilename");

		if (id == 0) {
			id = idclick;
		}

		if (id != 0) {
			List<User> retrive = db.getUserpdf(id);
			db.close();
			for (User u : retrive) {
				bt = u.getImgProfile();
				btn.setText("Update");
				RelativeLayout r = (RelativeLayout) findViewById(R.id.reltavi);
				r.setBackgroundDrawable(null);
				t.setText("Update Your Photo");
				if (bt == null) {
					bmp = null;
				} else {
					bmp = BitmapFactory.decodeByteArray(u.getImgProfile(), 0,
							u.getImgProfile().length);
				}
			}
			Drawable d = new BitmapDrawable(bmp);

			if (bmp != null) {
				iv.setImageDrawable(d);
			}
		}
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						PhotoUpload.this);
				alertDialog.setTitle("Choose Action");
				alertDialog.setPositiveButton("Camera",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent in = new Intent(
										android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
								startActivityForResult(in, 1);
							}
						});
				alertDialog.setNegativeButton("Gallery",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent in = new Intent(
										Intent.ACTION_PICK,
										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
								startActivityForResult(in, 2);
							}
						});
				alertDialog.show();
			}
		});

		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (pv == 0) {
					iv.setBackgroundColor(Color.TRANSPARENT);
					iv.buildDrawingCache();
					bimg = iv.getDrawingCache();
					ByteArrayOutputStream byteAOS = new ByteArrayOutputStream();
					bimg.compress(Bitmap.CompressFormat.PNG, 100, byteAOS);
					byte[] b1;
					if (iv.getTag() == "123") {
						b1 = byteAOS.toByteArray();
					} else {
						b1 = null;
					}
					Log.i("img", String.valueOf(b1));
					int i = db.addUser(new User(str, b1));
					db.close();
					Intent intent = new Intent(getApplicationContext(),
							TabClass.class);
					intent.putExtra("id", i);
					Toast.makeText(getApplicationContext(), "Welcome " + str,
							Toast.LENGTH_SHORT).show();
					startActivity(intent);
					finish();
				} else if (pv != 0) {
					iv.setBackgroundColor(Color.TRANSPARENT);
					iv.buildDrawingCache();
					bimg = iv.getDrawingCache();
					ByteArrayOutputStream byteAOS = new ByteArrayOutputStream();
					bimg.compress(Bitmap.CompressFormat.PNG, 100, byteAOS);
					byte[] b1;
					if (iv.getTag() == "123") {
						b1 = byteAOS.toByteArray();
					} else {
						b1 = null;
					}

					db.updateUserProfile(new User(id, b1));
					Toast.makeText(getApplicationContext(), "Photo Updated ",
							Toast.LENGTH_SHORT).show();
					db.close();
				}
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == 1) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			iv.setTag("123");
			iv.setImageBitmap(imageBitmap);

		}

		else if (resultCode == RESULT_OK && requestCode == 2) {
			Uri selectedImage = data.getData();
			String[] filepathColumn = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(selectedImage,
					filepathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filepathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			Bitmap bmp = BitmapFactory.decodeFile(picturePath);
			iv.setTag("123");
			iv.setImageBitmap(bmp);

		}
	}

	public void initalize() {
		btn = (Button) findViewById(R.id.btnNextImage);
		iv = (ImageView) findViewById(R.id.imgUploadPhoto);
		t = (TextView) findViewById(R.id.txtAddyourPhoto);
		db = new DatabaseHandler(this);

	}

}
