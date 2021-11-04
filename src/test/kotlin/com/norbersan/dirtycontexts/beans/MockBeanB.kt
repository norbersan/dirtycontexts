package com.norbersan.dirtycontexts.beans

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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockBeanB {
    @Autowired
    lateinit var beanContainer: BeanContainer

    @MockkBean
    lateinit var beanB: BeanB

    @Autowired
    lateinit var ctx: ConfigurableApplicationContext

    @Test
    fun mockBeanB(){
        every { beanB.toString() } answers { "mocked BeanB"}

        println()
        println("----------------------------")
        println("==> MOCKING BeanB")
        println("==> using App Context displayname=${shortName(ctx.displayName)}, in class ${this::class.java.simpleName}")
        println("==> running in thread ${Thread.currentThread()}")
        println("==> $beanContainer")
        println("==> ${beanContainer.beanA}")
        println("==> ${beanContainer.beanB}")
        println("==> ${beanContainer.beanC}")
        println("----------------------------")

        ctx.beanFactory.destroyBean(BeanA::class.java)
        ctx.beanFactory.destroyBean(BeanC::class.java)
    }
}