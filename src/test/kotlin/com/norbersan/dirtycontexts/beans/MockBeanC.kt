package com.norbersan.dirtycontexts.beans

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext

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
        println("==> using App Context displayname=${shortName(ctx.displayName)}, in class ${this::class.java.simpleName}")
        println("==> running in thread ${Thread.currentThread()}")
        println("==> $beanContainer")
        println("==> ${beanContainer.beanA}")
        println("==> ${beanContainer.beanB}")
        println("==> ${beanContainer.beanC}")
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