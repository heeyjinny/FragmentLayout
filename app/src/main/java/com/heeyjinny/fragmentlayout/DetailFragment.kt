package com.heeyjinny.fragmentlayout

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heeyjinny.fragmentlayout.databinding.FragmentDetailBinding

//1
//사용하지 않는 코드들 삭제(onCreateView()만 남김)
class DetailFragment : Fragment() {

    //2
    //back버튼 클릭 시 ListFragment로 돌아가는 코드 작성

    //2-1
    //앞 ListFragment 코드와 다르게 사용...
    //메인 액티비티를 담아두는 변수 선언: lateinit사용
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //2-4
        //인플레이트한 레이아웃을 binding변수에 담고 버튼에 리스너 등록 후
        //mainActivity의 goBack() 메서드 호출하고 root 리턴
        //return inflater.inflate(R.layout.fragment_detail, container, false)
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener { mainActivity.goBack() }
        return binding.root

        //3
        //에뮬레이터를 통해 프래그먼트간에 화면전환이 잘 되는 것을 확인할 수 있음
        //이제 프래그먼트로 값 전달하는 것을 구현하기 위해 MainActivity.kt수정

    }//onCreateView

    //2-2
    //onAttach()메서드 오버라이드하고 context를 MainActivity로 캐스팅하여
    //미리 선언한 변수 mainActivity에 담기
    override fun onAttach(context: Context) {
        super.onAttach(context)

        //2-3
        //앞 ListFragment 코드와 다르게 사용...
        //if문으로 타입비교 대신 as키워드 사용: 타입캐스팅(형변환)
        mainActivity = context as MainActivity
    }

    //1-1
    //fragment_detail.xml 수정

}//class...