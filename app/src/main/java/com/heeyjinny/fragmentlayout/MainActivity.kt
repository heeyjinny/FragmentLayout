package com.heeyjinny.fragmentlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.heeyjinny.fragmentlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //4-2
    //전역변수 listFragment lateinit으로 생성
    lateinit var listFragment:ListFragment

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

        //4
        //SEND 버튼 클릭 시 listFragment를 통해 가지고 있는 setValue()메서드 호출
        binding.btnSend.setOnClickListener {
            //4-1
            //setFragment()메서드 안에 변수로 선언된 val listFragment를 밖으로 빼서
            //전역변수 var(프로퍼티)로 만들어 클래스 안에서 모두 사용할 수 있게 수정

            //4-4
            //버튼 클릭 시 리스트프래그먼트의 setValue메서드에 값을 파라미터로 전달해 호출
            listFragment.setValue("액티비티에서 온 값...")

            //FIN
            //에뮬레이터 실행 후 확인...
        }

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
        //val listFragment: ListFragment = ListFragment()
        //4-3
        //생성한 ListFragment()의 변수를 밖에서 변수선언 했기 때문에 val예약어 삭제
        listFragment = ListFragment()

        //3
        //프래그먼트로 값 전달하기
        //값 전달 방법: 프래그먼트 생성 시 값 전달, 이미 생성되어있는 프래그먼트에 값 전달

        //3-1
        //프래그먼트 생성 시 값 전달하기
        //프래그먼트를 생성하면서 액티비티에 있는 값을 프래그먼트에 전달하는 방법으로
        //안드로이드에서 arguments제공
        //arguments: 프래그먼트의 기본 프로퍼티, 선언 없이 사용 가능
        //Bundle을 arguments에 전달하면 생성된 프래그먼트에서 arguments로 꺼낼 수 있음
        //
        //번들 생성 후 전달할 값 담기(put함수 사용 / 키 값, 전달 값)
        var bundle = Bundle()
        bundle.putString("key1", "프래그먼트 생성 시 값 전달")
        bundle.putInt("key2", 20220101)

        //3-2
        //값이 담긴 번들을 생성한 리스트 프래그먼트의 arguments에 담기
        listFragment.arguments = bundle

        //3-3
        //리스트프래그먼트의 레이아웃 파일 fragment_list.xml에
        //위 번틀 값을 보여줄 텍스트 뷰 2개 추가

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