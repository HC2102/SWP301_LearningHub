package swp391.group2.learninghub.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import swp391.group2.learninghub.Model.User_Table;
import swp391.group2.learninghub.Service.UserService;

@Configuration
public class DataBase {
    private static  final Logger logger= LoggerFactory.getLogger(DataBase.class);
    @Bean
    CommandLineRunner initDataBase(UserService userService) {
            //logger
            return new CommandLineRunner() {
                @Override
                public void run(String... args) throws Exception {
                    User_Table userTableA =new User_Table("huy.tranquang@gamil.com","Trần Huy","123456789",
                            "2","huyquang1",true,"22-02-2022");
                    User_Table userTableB =new User_Table("huy.tranquang02@gamil.com","Trần Huy2","123456789",
                            "4","huyquang02",true,"22-02-2022");
                    logger.info("insert data"+userService.save(userTableA));
                    logger.info("insert data"+userService.save(userTableB));
                }
            };
        }

}
