package softocean.app.kotlinmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import softocean.app.kotlinmvvm.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, MainFragment()).commit()
    }
}
