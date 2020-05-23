package com.github.yuizho.springsessionsample

import org.springframework.boot.web.servlet.ServletContextInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.servlet.ServletContext
import javax.servlet.SessionTrackingMode

@Configuration
class SpringSessionSampleConfig {
    @Bean
    fun servletContextInitializer(): ServletContextInitializer {
        // デフォルトの設定で使っていると初回初回ログイン時など初めてCookieが入るときに
        // GETリクエストのURLにjsessionidが入ってしまうので設定を追加する
        // https://qiita.com/dmnlk/items/b7d189d4dc09df1ee6b6#url%E3%81%ABjsessionid%E3%82%92%E4%BB%98%E4%B8%8E%E3%81%97%E3%81%AA%E3%81%84
        return object: ServletContextInitializer {
            override fun onStartup(servletContext: ServletContext?) {
                servletContext?.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE))
            }

        }
    }
}