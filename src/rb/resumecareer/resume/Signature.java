package rb.resumecareer.resume;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import java.util.List;

import rb.resumecareer.resumeDatabase.DatabaseHandler;
import rb.resumecareer.resumePojo.SignaturePojo;
import rb.resumecareer.resumePojo.User;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Signature extends Activity {

	LinearLayout mContent;
	signature mSignature;
	Button mGetSign, mClear;
	ImageView date1;
	String temp1, temp11;
	private int mYear, mMonth, mDay;
	public static String tempDir;
	public int count = 1;
	public String current = null;
	View mView;
	String mname;
	File mypath;
	final DatabaseHandler db = new DatabaseHandler(this);
	int i, id;
	byte[] b1;
	Bitmap bimg;
	private EditText place, dt, sincerely;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_signature);
		date1 = (ImageView) findViewById(R.id.imgCalendarsign);
		dt = (EditText) findViewById(R.id.edtSignatureDate);
		place = (EditText) findViewById(R.id.edtSignaturePlace);
		sincerely = (EditText) findViewById(R.id.edtSignatureYourSincerely);
		mContent = (LinearLayout) findViewById(R.id.linearLayout);
		mClear = (Button) findViewById(R.id.btnSignatureClear);
		mGetSign = (Button) findViewById(R.id.btnSignatureSave);
		mSignature = new signature(this, null);
		mSignature.setBackgroundColor(Color.WHITE);
		mContent.addView(mSignature, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		mSignature.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					// Disallow ScrollView to intercept touch events.
					v.getParent().requestDisallowInterceptTouchEvent(true);
					break;

				case MotionEvent.ACTION_UP:
					// Allow ScrollView to intercept touch events.
					v.getParent().requestDisallowInterceptTouchEvent(false);
					break;
				}

				// Handle ListView touch events.
				v.onTouchEvent(event);
				return true;
			}
		});
		date1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				// Launch Date Picker Dialog

				DatePickerDialog dpd = new DatePickerDialog(Signature.this,
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								// Display Selected date in textbox
								temp11 = monthGet(monthOfYear + 1);
								Signature.this.dt.setText(dayOfMonth + " " + temp11
										+ " " + year);
							}
						}, mYear, mMonth, mDay);
				dpd.show();

			}

		});
		Intent in = getIntent();
		id = in.getIntExtra("id", 0);
		final int idclick = in.getIntExtra("idclick", 0);
		
		if (idclick != 0) {
			
			id = idclick;
			List<User> user = db.getUserpdf(id);
			for (User c : user) {
				mname = c.getName();
			}
			db.close();
			tempDir = Environment.getExternalStorageDirectory() + "/"
					+ getResources().getString(R.string.external_dir) + "/";

			ContextWrapper cw = new ContextWrapper(getApplicationContext());
			File directory = cw.getDir(
					getResources().getString(R.string.external_dir),
					Context.MODE_PRIVATE);

			Bitmap bmp1 = null;
			List<SignaturePojo> retrivedata = db
					.getAllSignatureDetailPdf(idclick);

			for (SignaturePojo p : retrivedata) {

				place.setText(p.getPlace().toString());
				sincerely.setText(p.getSincerely().toString());
				dt.setText(p.getDt().toString());
				bmp1 = BitmapFactory.decodeByteArray(p.getSign(), 0,
						p.getSign().length);
			}
			Drawable d = new BitmapDrawable(bmp1);
			
			int c = Color.WHITE;
			

			if (bmp1 == null) {
				mSignature.setBackgroundColor(Color.WHITE);
			} else {
				mSignature.setBackgroundDrawable(d);
				mGetSign.setText("Update");
			}
			mContent.removeAllViews();
			mContent.addView(mSignature, LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT);
			if (d.equals(null)) {
				mGetSign.setEnabled(false);
			} else
				mGetSign.setEnabled(true);
			mView = mSignature;
		
		} else {
			List<User> user = db.getUserpdf(id);
			for (User c : user) {
				mname = c.getName();
			}
			db.close();
			tempDir = Environment.getExternalStorageDirectory() + "/"
					+ getResources().getString(R.string.external_dir) + "/";

			ContextWrapper cw = new ContextWrapper(getApplicationContext());
			File directory = cw.getDir(
					getResources().getString(R.string.external_dir),
					Context.MODE_PRIVATE);

			mContent.removeAllViews();
			mSignature.setBackgroundColor(Color.WHITE);
			mContent.addView(mSignature, LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT);
			// mGetSign.setEnabled(false);
			mView = mSignature;
		}
		db.close();
		mClear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mContent.removeAllViews();
				mSignature.setBackgroundColor(Color.WHITE);
				mSignature.clear();
				mContent.addView(mSignature, LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT);

			}
		});

		mGetSign.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				boolean error = captureSignature();
				if (!error) {
					mView.setDrawingCacheEnabled(true);
					/* mSignature.save(mView); */
					Bundle b = new Bundle();
					b.putString("status", "done");
					Intent intent = new Intent();
					intent.putExtras(b);
					setResult(RESULT_OK, intent);

					bimg = mSignature.getDrawingCache();
					ByteArrayOutputStream byteAOS = new ByteArrayOutputStream();
					bimg.compress(Bitmap.CompressFormat.PNG, 100, byteAOS);
					b1 = byteAOS.toByteArray();
					if (mGetSign.getText().toString()
							.equalsIgnoreCase("Update")) {
						int d = db.updateSignatureDetail(new SignaturePojo(id,
								place.getText().toString(), dt.getText()
										.toString(), sincerely.getText()
										.toString(), b1));
						Toast.makeText(getApplicationContext(),
								"Capture updated", Toast.LENGTH_SHORT).show();
					} else {
						int in = db.addSignature(new SignaturePojo(id, place
								.getText().toString(), dt.getText().toString(),
								sincerely.getText().toString(), b1));
						if (in >= 1) {
							mGetSign.setText("Update");

						}
						Toast.makeText(getApplicationContext(), "Capture",
								Toast.LENGTH_SHORT).show();
					}
					db.close();
				}

			}
		});

	}

	private boolean captureSignature() {

		boolean error = false;
		String errorMessage = "";

		if (error) {
			Toast toast = Toast
					.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 105, 50);
			toast.show();
		}

		return error;
	}

	public class signature extends View {
		private static final float STROKE_WIDTH = 5f;
		private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
		private Paint paint = new Paint();
		private Path path = new Path();

		private float lastTouchX;
		private float lastTouchY;
		private final RectF dirtyRect = new RectF();

		public signature(Context context, AttributeSet attrs) {
			super(context, attrs);
			paint.setAntiAlias(true);
			paint.setColor(Color.BLACK);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(STROKE_WIDTH);
		}

		public void clear() {
			path.reset();
			invalidate();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawPath(path, paint);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float eventX = event.getX();
			float eventY = event.getY();
			mGetSign.setEnabled(true);

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				path.moveTo(eventX, eventY);
				lastTouchX = eventX;
				lastTouchY = eventY;
				return true;

			case MotionEvent.ACTION_MOVE:

			case MotionEvent.ACTION_UP:

				resetDirtyRect(eventX, eventY);
				int historySize = event.getHistorySize();
				for (int i = 0; i < historySize; i++) {
					float historicalX = event.getHistoricalX(i);
					float historicalY = event.getHistoricalY(i);
					expandDirtyRect(historicalX, historicalY);
					path.lineTo(historicalX, historicalY);
				}
				path.lineTo(eventX, eventY);
				break;

			default:
				debug("Ignored touch event: " + event.toString());
				return false;
			}

			invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
					(int) (dirtyRect.top - HALF_STROKE_WIDTH),
					(int) (dirtyRect.right + HALF_STROKE_WIDTH),
					(int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

			lastTouchX = eventX;
			lastTouchY = eventY;

			return true;
		}

		private void debug(String string) {
		}

		private void expandDirtyRect(float historicalX, float historicalY) {
			if (historicalX < dirtyRect.left) {
				dirtyRect.left = historicalX;
			} else if (historicalX > dirtyRect.right) {
				dirtyRect.right = historicalX;
			}

			if (historicalY < dirtyRect.top) {
				dirtyRect.top = historicalY;
			} else if (historicalY > dirtyRect.bottom) {
				dirtyRect.bottom = historicalY;
			}
		}

		private void resetDirtyRect(float eventX, float eventY) {
			dirtyRect.left = Math.min(lastTouchX, eventX);
			dirtyRect.right = Math.max(lastTouchX, eventX);
			dirtyRect.top = Math.min(lastTouchY, eventY);
			dirtyRect.bottom = Math.max(lastTouchY, eventY);
		}
	}

	protected String monthGet(int month) {
		String temp = null;
		if (month == 1) {
			temp = "JANUARY";
		} else if (month == 2) {
			temp = "FEBRUARY";
		} else if (month == 3) {
			temp = "MARCH";
		} else if (month == 4) {
			temp = "APRIL";
		} else if (month == 5) {
			temp = "MAY";
		} else if (month == 6) {
			temp = "JUNE";
		} else if (month == 7) {
			temp = "JULY";
		} else if (month == 8) {
			temp = "AUGUST";
		} else if (month == 9) {
			temp = "SEPTEMBER";
		} else if (month == 10) {
			temp = "OCTOBER";
		} else if (month == 11) {
			temp = "NOVEMBER";
		} else if (month == 12) {
			temp = "DECEMBER";
		}
		return temp;

	}
}
