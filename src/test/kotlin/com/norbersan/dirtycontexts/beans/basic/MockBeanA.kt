package com.norbersan.dirtycontexts.beans.basic

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
class MockBeanA {
    @Autowired
    lateinit var beanContainer: BeanContainer

    @MockkBean
    lateinit var beanA: BeanA

    @Autowired
    lateinit var ctx: ConfigurableApplicationContext

    @Test
    fun mockBeanA(){
        every { beanA.toString() } answers { "mocked BeanA"}

        println()
        println("----------------------------")
        println("==> MOCKING BeanA")
        println("==> running in thread ${Thread.currentThread()}")
        println("==> " + beanContainer.beanA.toString())
        println("==> " + beanContainer.beanB.toString())
        println("==> " + beanContainer.beanC.toString())
        println("----------------------------")
        ctx.beanFactory.destroyBean(beanContainer.beanB)
        ctx.beanFactory.destroyBean(beanContainer.beanC)
    }
}