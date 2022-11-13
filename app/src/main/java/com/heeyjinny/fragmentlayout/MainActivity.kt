package com.heeyjinny.fragmentlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.heeyjinny.fragmentlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩
        setContentView(binding.root)

        //프래그먼트 생성
        //java디렉터리 밑 패키지명 우클릭 - New - Fragment - Fragment(Blank)
        //ListFragment.kt 생성

        //onCreate()에서 프래그먼트 호출
        setFragment()




    }//onCreate

    //ListFragment.kt를 액티비티에 삽입하는 메서드 setFragment()작성
    //onCreate()에서 미리 호출
    fun setFragment() {
        //액티비티에 프래그먼트를 삽입하기 위해서는
        //프래그먼트 매니저를 통해 삽입할 레이아웃의 id를 지정함

        //트랜잭션: 삽입 또는 삭제를 하는 도중에 문제가 발생하면 원래 상태로 되돌리기위해
        //모든 동작을 복구하는 하나의 작업 단위
        //프래그먼트 삽입 과정은 하나의 트랜잭션으로 관리됨
        //트랜잭션 매니저를 통한 트랜잭선 순서:
        //begin transaction -> add fragment -> commit transaction

        //ListFragment() 생성
        val listFragment: ListFragment = ListFragment()

        //액티비티가 가지고 있는 프래그먼트 매니저를 통해 트랜잭션을 시작하여 변수에 저장
        val transaction = supportFragmentManager.beginTransaction()

        //트랜잭션의 add()메서드 사용
        //레이아웃에 프래그먼트 연결...
        //activity_main.xml에 생성했던 id가 frameLayout인 레이아웃에
        //ListFragment.kt를 생성하여 저장하고 있는 변수 listFragment 삽입
        //transaction.add(R.id.frameLayout, listFragment)
        transaction.add(R.id.frameLayout, listFragment)

        //모든 작업이 정상적 처리되었음을 트랜잭션에 알려주기: commit()
        transaction.commit()

    }

}//MainActivity