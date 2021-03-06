package com.norbersan.dirtycontexts.beans

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import com.norbersan.dirtycontexts.beans.shortName
import org.springframework.context.annotation.Profile
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MockBeanA {
    @Autowired
    lateinit var beanContainer: BeanContainer

    @MockkBean
    lateinit var beanA: BeanA

    @Autowired
    lateinit var ctx: ConfigurableApplicationContext

    @Test
    //@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    //@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun mockBeanA(){
        every { beanA.toString() } answers { "mocked BeanA"}

        println()
        println("----------------------------")
        println("==> MOCKING BeanA")
        println("==> using App Context displayname=${shortName(ctx.displayName)}, in class ${this::class.java.simpleName}")
        println("==> running in thread ${Thread.currentThread()}")
        println("==> $beanContainer")
        println("==> ${beanContainer.beanA}")
        println("==> ${beanContainer.beanB}")
        println("==> ${beanContainer.beanC}")
        println("----------------------------")

        //ctx.beanFactory.destroyBean(beanContainer.beanB)
        //ctx.beanFactory.destroyBean(beanContainer.beanC)
    }
}