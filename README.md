# ViewActionBinder

Idea from butterknife but this is not working with Java CodeGeneration
# 1. view actions
> OnClick, OnTextChange, OnSeekChange, OnItemClick, OnCheckChange, OnItemSelect, OnLongClick, OnTouch

# Actions injected into activity
    public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Binder.instance().bind(this);
    }

    @OnClick(id = R.id.button2)
    public void onClick(){
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
    }
    
    @OnTouch(id = R.id.button)
    public void touched(MotionEvent event){
        Toast.makeText(this, "touched", Toast.LENGTH_SHORT).show();
    }

# Actions injected into viewModel
 activity for view model
    
    public class MainActivity extends ViewModelActivity<MainViewModel>
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel.bind();
    }

 and there is view model
    
    public class MainViewModel extends ActivityViewModel<MainActivity>

    public void bind(){
        Binder.instance(activity).bind(this);
    }

    @OnClick(id = R.id.button)
    public void onClick(){
        Intent intent = new Intent(activity, AnotherActivity.class);
        activity.startActivity(intent);
    }

# 2 injectable fields:
> Preference, File 

> usability, both as activity also view model. example: 
public class AnotherActivity extends AppCompatActivity

    @Preference(forName = "default")
    SharedPreferences preferences;

    @File(fileName = "lazy.pdf", storage = Storage.INTERNAL)
    java.io.File cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Binder.instance().bind(this);
    }

# 3 inject receiver method
for example

    public class AnotherActivity extends AppCompatActivity {
   
    @Receiver(actions = {"android.net.wifi.WIFI_STATE_CHANGED","android.net.wifi.STATE_CHANGE"})
    public void received(Intent data){
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }

# Try to avoid leaks with inject manager reject!
for example: 

    public class AnotherActivity extends AppCompatActivity {

    @Preference(forName = "default")
    SharedPreferences preferences;

    @File(fileName = "lazy.pdf", storage = Storage.INTERNAL)
    java.io.File cv;
    
    InjectManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        manager = Binder.instance().bind(this);
    }
    
    @Override
    public void onDestroy(){
        super.onDestroy();
        manager.reject();
    }
 
https://github.com/pirati02/ViewActionBinder/blob/master/app/src/main/java/com/dev/baqari/inject/MainViewModel.java
