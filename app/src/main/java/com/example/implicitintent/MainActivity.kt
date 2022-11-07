package com.example.implicitintent

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState !=null ){
            binding.tvMessage.text = savedInstanceState.getString("tvMessage")
        }

        Log.d("implicitIntent", "onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("implicitIntent", "onStart()")
    }
    //저장되어있던 값을 가져온다
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState !=null ){
            binding.tvMessage.text = savedInstanceState.getString("tvMessage")
        }
        Log.d("implicitIntent", "onRestoreInstanceState()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("implicitIntent", "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("implicitIntent", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("implicitIntent", "onStop()")
    }

    //값을 잃기 전에 번들로 저장해 둠.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("tvMessage", "${binding.tvMessage.text.toString()}")
        Log.d("implicitIntent", "onSaveInstanceState()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("implicitIntent", "onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("implicitIntent", "onRestart()")
    }

    fun webviewOnClick(view: View) {
        when (view.id) {

            R.id.btn_webView -> {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse("http://naver.com")
                startActivity(intent)
            }
            R.id.btn_googleMap -> {
                val latitude = 39.013647
                val logitude = 125.737647
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse("http://google.com/maps?q=${latitude},${logitude}")
                startActivity(intent)
            }
            R.id.btn_search -> {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.putExtra(SearchManager.QUERY, binding.edtSearch.text.toString())
                startActivity(intent)
            }
            R.id.btn_message -> {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.putExtra("sms_body", "화이팅")
                intent.data = Uri.parse("smsto:" + Uri.encode("010-3305-7868"))
                startActivity(intent)
            }
            R.id.btn_photo -> {
                val intent = Intent()
                intent.action = MediaStore.ACTION_IMAGE_CAPTURE
                startActivity(intent)
            }
            R.id.btn_user -> {
                val intent = Intent()
                intent.action = "ACTION_EDIT"
                intent.putExtra("name", "건강하세요")
                try {
                    startActivity(intent)
                } catch (e: java.lang.Exception){
                    Log.d("implicitIntent", "${e.stackTrace}")
                }

            }
            //서치(에딧텍스트뷰)의 스트링 값을 메세지(텍스트뷰)에 띄워줌
            R.id.btn_print -> {
                binding.tvMessage.text = binding.edtSearch.text.toString()
            }
            // 포커스를 이동하면서 자판을 띄워줌
            R.id.btn_focus -> {
                binding.edtFocus.requestFocus()
                val manager = getSystemService(INPUT_METHOD_SERVICE)as InputMethodManager
                manager.showSoftInput(binding.edtFocus,InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }
}