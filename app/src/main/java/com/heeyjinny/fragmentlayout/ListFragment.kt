package com.heeyjinny.fragmentlayout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heeyjinny.fragmentlayout.databinding.FragmentListBinding

//1
//사용하지 않는 코드들 삭제
class ListFragment : Fragment() {

    //2
    //프래그먼트에서 프래그먼트 실행하기...
    //ListFragment의 Next버튼 클릭 시 메인 액티비티의 goDetail()이 호출되어
    //DetailFragment가 실행되는 형태 작성

    //2-1
    //MainActivity를 담아둘 멤버변수 선언
    var mainActivity: MainActivity? = null

    //4-2
    //바인딩뷰를 전역변수로 바꿔줘야 다른 함수에도 인식함 lateinit을 사용해 프로퍼티로 만듦
    lateinit var binding: FragmentListBinding

    //1-1
    //onCreateView()의 파라미터
    //inflater: 레이아웃 파일을 로드하기 위한 레이아웃 인플레이터 기본제공
    //container: 프래그먼트 레이아웃이 배치되는 부모 레이아웃
    //savedInstanceState: 상태 값 저장을 위한 보조도구, 액티비티의 onCreate의 파라미터와 동일한동작
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //2-4
        //fragment_list.xml 의 Next버튼 클릭 시 메인 액티비티 goDetail()이 호출되어
        //DetailFragment가 실행되는 코드 작성
        //return binding.root: onCreateView의 반환값이 View? 이기 때문에 바인딩의 root뷰 넘겨줌
        //기존 리턴코드 삭제 후 바인딩 사용
        //return inflater.inflate(R.layout.fragment_list, container, false)

        //4-3 클래스 안에서 바인딩을 모두 사용할 수 있게 하기 위해 lateint으로 변수를 만들고 만들어 두었던 val예약어 삭제
        //val binding = FragmentListBinding.inflate(inflater, container, false)
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener { mainActivity?.goDetail() }

        //3
        //프래그먼트가 생성될 때 액티비티에서 전달받은 값을 꺼내 레이아웃 뷰에 보여주는 코드 작성
        //전달받은 값을 꺼낼 때는 액티비티의 intent처럼 arguments에서 값을 직접 꺼낼 수 있음
        //return binding.root 윗줄에 코드 작성
        //arguments에서 값을 꺼낸 후 레이아웃 텍스트 뷰에 입력
        binding.textTitle.text = arguments?.getString("key1")
        binding.textValue.text = "${arguments?.getInt("key2")}"
        //binding.textValue.text = arguments?.getInt("key2").toString()

        //3-1
        //이미 생성되어 화면에 보이는 프래그먼트의 텍스트뷰에 값 전달하기
        //파라미터를 통해 액티비티의 값을 전달하여 이미 보이는 뷰의 값을 변경할 수 있음
        //fragment_list.xml 파일 수정

        return binding.root

        //2-5
        //프래그먼트는 하나의 레이아웃에 투명하게 한 층씩 쌓이는 형태라서
        //에뮬레이터 실행 후 Next버튼 클릭 시 화면에 겹쳐보이기 때문에
        //기본 배경색을 설정해야 함
        //fragment_detail.xml 파일

        //1-2
        //fragment.xml(프래그먼트 레이아웃) 파일 작성

    }

    //2-2
    //onAttach()메서드 오버라이드
    //onAttach()메서드를 통해 넘어온 Context를 캐스팅하여 MainActivity에 담음
    //프래그먼트의 onAttach()메서드를 통해 넘어온 Context는 부모 액티비티 전체가 담겨 있음
    override fun onAttach(context: Context) {
        super.onAttach(context)

        //2-3
        //context타입이 MainActivity인 것을 확인 후 선언했던 mainActivity변수에 저장
        if(context is MainActivity) mainActivity = context

    }

    //4
    //액티비티로부터 전달받을(파라미터) 문자열 값을
    //텍스트뷰에(textFromActivity) 출력하는 메서드 setValue()생성
    fun setValue(value:String){
        //4-1
        //바인딩뷰를 전역변수로 바꿔줘야 다른 함수에도 인식함
        //4-4
        //텍스트뷰에 파라미터로 전달받은 값 출력
        binding.textFromActivity.text = value

    }

    //5
    //액티비티 화면에 버튼 추가 후 버튼을 눌렀을 때 액티비티에 설정한 값을
    //프래그먼트의 텍스트 뷰에 출력하도록 설정
    //activity_main.xml에 버튼 추가...

}//ListFragment