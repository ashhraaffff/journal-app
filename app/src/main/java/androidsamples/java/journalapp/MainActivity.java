package androidsamples.java.journalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  private NavController mNavController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
            .findFragmentById(R.id.nav_host_fragment);
    if (navHostFragment != null) {
      mNavController = navHostFragment.getNavController();
      NavigationUI.setupActionBarWithNavController(this, mNavController);
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    return mNavController.navigateUp() || super.onSupportNavigateUp();
  }
}