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

        //1
        //프래그먼트 생성
        //java디렉터리 밑 패키지명 우클릭 - New - Fragment - Fragment(Blank)
        //ListFragment.kt 생성

        //1-2
        //onCreate()에서 프래그먼트 호출
        setFragment()

    }//onCreate

    //1-1
    //ListFragment를 액티비티에 삽입하는 메서드 setFragment()작성
    //onCreate()에서 미리 호출
    fun setFragment() {

        //1-3
        //액티비티에 프래그먼트를 삽입하기 위해서는
        //프래그먼트 매니저를 통해 삽입할 레이아웃의 id를 지정함
        //
        //트랜잭션: 삽입 또는 삭제를 하는 도중에 문제가 발생하면 원래 상태로 되돌리기위해
        //모든 동작을 복구하는 하나의 작업 단위
        //프래그먼트 삽입 과정은 하나의 트랜잭션으로 관리됨
        //트랜잭션 매니저를 통한 트랜잭선 순서:
        //begin transaction -> add fragment -> commit transaction
        //
        //ListFragment() 생성
        val listFragment: ListFragment = ListFragment()

        //1-4
        //액티비티가 가지고 있는 프래그먼트 매니저를 통해 트랜잭션을 시작하여 변수에 저장
        val transaction = supportFragmentManager.beginTransaction()

        //1-5
        //트랜잭션의 add()메서드 사용
        //레이아웃에 프래그먼트 연결...
        //activity_main.xml에 생성했던 id가 frameLayout인 레이아웃에
        //ListFragment.kt를 생성하여 저장하고 있는 변수 listFragment 삽입
        //transaction.add(R.id.frameLayout, listFragment)
        transaction.add(R.id.frameLayout, listFragment)

        //1-6
        //모든 작업이 정상적 처리되었음을 트랜잭션에 알려주기: commit()
        transaction.commit()

    }

    //2
    //프래그먼트 화면전환하기
    //ListFragment의 Next버튼을 클릭하면 DetailPragment로 화면 전환
    //java디렉터리 밑 패키지명 우클릭 - New - Fragment - Fragment(Blank)
    //DetailPragment.kt 생성

    //2-1
    //DetailFragment를 액티비티에 삽입하는 메서드 goDtail()작성
    fun goDetail(){

        //2-2
        //detailFragment변수를 생성하여 DetailFragment저장
        val detailFragment = DetailFragment()

        //2-3
        //액티비티에 DetailFragment를 가지고 있는 변수 detailFragment삽입
        //트랜젝션 매니저를 통해 변수 생성하여 begin(시작) - add(추가) - commit(처리)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, detailFragment)

        //2-4
        //프래그먼트 트랜젝션 백스택에 담기
        //addToBackStack()을 사용하여 스마트폰 뒤로가기 버튼 클릭 시 프래그먼트 화면만 제거 가능
        //만약 백스택에 담지 않았을 때 스마트폰 뒤로가기 버튼을 클릭 시 액티비티가 종료됨
        //트랜젝션의 add와 commit사이에 추가
        transaction.addToBackStack("detail")

        //2-3
        transaction.commit()

    }

    //2-5
    //fragment_detail.xml의 Back버튼 클릭할 때 호출되는 메서드 작성: goBack()
    //버튼은 DetailFragment에 있지만 코드는 프래그먼트를 보여주는 액티비티에서 작성
    fun goBack(){

        //2-6
        //뒤로 돌아가는 코드는 트랜잭션 없이 뒤로가기 기본 메서드로 간단하게 처리가능
        //onBackPressed(): 뒤로가기 필요시 사용하는 액티비티 기본 메서드
        onBackPressed()

        //2-7
        //프래그먼트에서 프래그먼트 실행하기...
        //ListFragment의 Next버튼 클릭 시 메인 액티비티의 goDetail()이 호출되어
        //DetailFragment가 실행되는 형태 작성
        //ListFragment.kt 코드 수정

    }




}//MainActivity