package com.norbersan.dirtycontexts.beans

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MockNoBean {
    @Autowired
    lateinit var beanContainer: BeanContainer

    @Autowired
    lateinit var ctx: ConfigurableApplicationContext

    @Test
    //@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun mockNoBean1(){
        mockNoBean(1)
    }

    @Test
    @Disabled
    fun mockNoBean2(){
        mockNoBean(2)
    }

    @Test
    @Disabled
    fun mockNoBean3(){
        mockNoBean(3)
    }

    private fun mockNoBean(nr: Int){
        println()
        println("----------------------------")
        println("==> NO MOCKING #$nr")
        println("==> using App Context displayname=${shortName(ctx.displayName)}, in class ${this::class.java.simpleName}")
        println("==> running in thread ${Thread.currentThread()}")
        println("==> $beanContainer")
        println("==> ${beanContainer.beanA}")
        println("==> ${beanContainer.beanB}")
        println("==> ${beanContainer.beanC}")
        println("----------------------------")
    }
}