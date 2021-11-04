package com.norbersan.dirtycontexts.beans

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
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
class MockNoBean {
    @Autowired
    lateinit var beanContainer: BeanContainer

    @Autowired
    lateinit var ctx: ConfigurableApplicationContext

    @Test
    //@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun mockNoBean(){
        println()
        println("----------------------------")
        println("==> NO MOCKING")
        println("==> using App Context displayname=${shortName(ctx.displayName)}, in class ${this::class.java.simpleName}")
        println("==> running in thread ${Thread.currentThread()}")
        println("==> $beanContainer")
        println("==> ${beanContainer.beanA}")
        println("==> ${beanContainer.beanB}")
        println("==> ${beanContainer.beanC}")
        println("----------------------------")
    }
}