package com.norbersan.dirtycontexts.beans.basic

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
class MockBeanC {
    @Autowired
    lateinit var beanContainer: BeanContainer

    @MockkBean
    lateinit var beanC: BeanC

    @Autowired
    lateinit var ctx: ConfigurableApplicationContext

    @Test
    fun mockBeanC(){
        every { beanC.toString() } answers { "mocked BeanC"}

        println()
        println("----------------------------")
        println("==> MOCKING BeanC")
        println("==> running in thread ${Thread.currentThread()}")
        println("==> " + beanContainer.beanA.toString())
        println("==> " + beanContainer.beanB.toString())
        println("==> " + beanContainer.beanC.toString())
        println("----------------------------")

        ctx.beanFactory
                .beanDefinitionNames
                .forEach {
                    if (it.contains("norbersan")){
                        println("==> found $it")
                        ctx.beanFactory.destroyBean(it)
                    }
                }
    }
}