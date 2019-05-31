package softocean.app.kotlinmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import softocean.app.kotlinmvvm.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tag = "my_fragment"
        val fragmentManager = supportFragmentManager
        if (fragmentManager.findFragmentByTag(tag) == null) {
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container, MainFragment(),tag).commit()
        }

    }
}
