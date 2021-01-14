/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2.basic

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        container = findViewById(R.id.fragment_container)
    }

    override fun onResume() {
        super.onResume()
        // systemUiVisibility()을 호출하여 전체화면 모드로 전환할 수 있다.
        // 전체 화면 플래그를 설정하기 전에 UI가 안정화되도록 잠시 기다려야한다.
        // 그렇지 않으면 준비되기 전에 immersive 모드로 앱이 설정될 수 있으며, 플래그가 고정되지 않다.
        // 참고 : Android에서는 lean back, Immersive, sticky immersive라는 3가지 옵션으로 앱을 전체 화면 모드로 전환할 수 있다.
        container.postDelayed({
            container.systemUiVisibility = FLAGS_FULLSCREEN
        }, IMMERSIVE_FLAG_TIMEOUT)
    }

    companion object {
        /** 액티비티를 몰입형 모드에 넣는데 요구되는 모든 필수 플래그 조합
         * TODO : 작업 표시줄과 다른 UI 컨트롤도 동시에 숨겨지는지 반드시 확인
         * */
        const val FLAGS_FULLSCREEN =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or // title, status bar(최상단 화면 위치) 가리
                        View.SYSTEM_UI_FLAG_FULLSCREEN or // 네비게이션 버튼과 상태바를 살짝 어둡게 처리함
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or // 시스템 표시줄이 숨겨졌다가 표시될 때 레이아웃의 크기가 조절되는 것을 방지
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY // sticky immersive 모드

        /** UI 애니메이션에 사용되는 Milliseconds */
        const val ANIMATION_FAST_MILLIS = 50L
        const val ANIMATION_SLOW_MILLIS = 100L
        private const val IMMERSIVE_FLAG_TIMEOUT = 500L
    }
}
