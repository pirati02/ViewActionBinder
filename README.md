# ViewActionBinder

idea from butterknife but this isnot working with Java CodeGeneration
# 1. view actions
> OnClick, OnTextChange, OnSeekChange, OnItemClick, OnCheckChange, OnItemSelect, OnLongClick, OnTouch

# actions injected into activity
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
    
    @OnTouch(id - R.id.button)
    public void touched(MotionEvent event){
        Toast.makeText(this, "touched", Toast.LENGTH_SHORT).show();
    }

# actions injected into viewModel
 activity for view model
> public class MainActivity extends ViewModelActivity<MainViewModel>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel.bind();
    }

and there is view model
> public class MainViewModel extends ActivityViewModel<MainActivity>

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
# try to avoid leaks
for example: 
public class AnotherActivity extends AppCompatActivity
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
