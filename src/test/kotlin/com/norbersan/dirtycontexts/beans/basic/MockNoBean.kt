package com.norbersan.dirtycontexts.beans.basic

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
class MockNoBean {
    @Autowired
    lateinit var beanContainer: BeanContainer

    @Autowired
    lateinit var ctx: ConfigurableApplicationContext

    @Test
    fun mockNoBean(){
        println()
        println("----------------------------")
        println("==> NO MOCKING")
        println("==> running in thread ${Thread.currentThread()}")
        println("==> " + beanContainer.beanA.toString())
        println("==> " + beanContainer.beanB.toString())
        println("==> " + beanContainer.beanC.toString())
        println("----------------------------")
    }
}