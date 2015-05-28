//
//import org.junit.Assert;
//
//import static org.junit.Assert.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import wadp.Application;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//
//
//@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration
//@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class RegisterControllerTest {
//    private final String here = "/register";
//    
// 
//    
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    
//    
//
//    
//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//                
//    }
//
//    
//
//   @Test
//    public void RegisterShowsPage() throws Exception {
//        MvcResult res = mockMvc.perform(get(here))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = res.getResponse().getContentAsString();
//        Assert.assertTrue(content.contains("Tavoitemittari"));
//
//    }
//
//
//    
//    @Test
//    public void RegisterationSucceedsWithCorrectInfo() throws Exception {
//      this.mockMvc.perform(post(here)
//               .param("name", "matti meikäläinen")
//               .param("email", "matti@matti.com")
//               .param("confirmemail", "matti@matti.com")
//               .param("password", "salasana")
//               .param("confirmpassword", "salasana")
//               .param("userRole", "teacher"))
//               .andDo(print())
//               .andExpect(redirectedUrl("welcome"));
//                
//               
//   }
//    
//    
//    @Test
//    public void UserConfirmsEmailAddressWrong() throws Exception {
//      MvcResult res = this.mockMvc.perform(post(here)
//              .param("name", "jukka")
//              .param("email", "jukka@matti.com")
//              .param("confirmemail", "jukkka@matti.com")
//              .param("password", "salasana")
//              .param("confirmpassword", "salasana")
//              .param("userRole", "teacher"))
//              .andDo(print())
//              .andReturn();
//
//              String content = res.getResponse().getContentAsString();
//              Assert.assertTrue(content.contains("Varmista"));
//   }
//
//    @Test
//    public void UserConfirmsPasswordWrong() throws Exception {
//      MvcResult res = this.mockMvc.perform(post(here)
//              .param("name", "olli")
//              .param("email", "olli@matti.com")
//              .param("confirmemail", "olli@matti.com")
//              .param("password", "salasana")
//              .param("confirmpassword", "salainensana")
//              .param("userRole", "teacher"))
//              .andDo(print())
//              .andReturn();
//
//              String content = res.getResponse().getContentAsString();
//              Assert.assertTrue(content.contains("Varmista"));
//   }
//
//      
//    @Test
//    public void EmailAlreadyRegistered() throws Exception {
//        
//      this.mockMvc.perform(post(here)
//              .param("name", "ari")
//              .param("email", "ari@matti.com")
//              .param("confirmemail", "ari@matti.com")
//              .param("password", "salasana")
//              .param("confirmpassword", "salasana")
//              .param("userRole", "teacher"))
//              .andDo(print())
//              .andReturn();
//      
//               MvcResult res = this.mockMvc.perform(post(here)
//              .param("name", "ari")
//              .param("email", "ari@matti.com")
//              .param("confirmemail", "ari@matti.com")
//              .param("password", "salasana")
//              .param("confirmpassword", "salasana")
//              .param("userRole", "teacher"))
//              .andDo(print())
//              .andReturn();
//               String content = res.getResponse().getContentAsString();
//               Assert.assertTrue(content.contains("Sähköpostiosoite on jo rekisteröity palveluun"));
//   }
//    
//    
//    
//
//
//}