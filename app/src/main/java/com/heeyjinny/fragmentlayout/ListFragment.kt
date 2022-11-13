package com.heeyjinny.fragmentlayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//사용하지 않는 코드들 삭제
class ListFragment : Fragment() {
    //onCreateView()의 파라미터
    //inflater: 레이아웃 파일을 로드하기 위한 레이아웃 인플레이터 기본제공
    //container: 프래그먼트 레이아웃이 배치되는 부모 레이아웃
    //savedInstanceState: 상태 값 저장을 위한 보조도구, 액티비티의 onCreate의 파라미터와 동일한동작
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)

        //fragment.xml(프래그먼트 레이아웃) 파일 작성

    }
}//ListFragment