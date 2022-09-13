package com.example.booksstory.loader;

import com.example.booksstory.entity.Admin;
import com.example.booksstory.entity.Role;
import com.example.booksstory.repository.AdminRepository;
import com.example.booksstory.repository.RoleRepository;
import com.example.booksstory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;


    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    public DataLoader(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, AdminRepository adminRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("keldi");

        try {
            if (init.equalsIgnoreCase("create")) {

                Role roleUser=new Role();

                roleUser.setName("ROLE_USER");
                roleUser.setId(1L);

                Role roleAdmin = new Role();
                roleAdmin.setId(2L);
                roleAdmin.setName("ROLE_ADMIN");

                Role roleCreate=new Role();
                roleCreate.setId(3L);
                roleCreate.setName("ROLE_CREATE");


                List<Role> roleList = new ArrayList<>(Arrays.asList(roleUser, roleAdmin, roleCreate));
                roleRepository.saveAll(roleList);

                Admin admin = new Admin();
                admin.setIsAdmin(true);
                admin.setUsername("creator");
                admin.setEmail("test777@gmail.com");
                admin.setPassword(passwordEncoder.encode("create123"));
                admin.setSocial("t.me/test");
                admin.setRoles(roleList);
                adminRepository.save(admin);

            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
