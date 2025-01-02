package com.MotherSon.CRM.config;
 
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
 
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import com.MotherSon.CRM.models.Company;
import com.MotherSon.CRM.models.Departments;
import com.MotherSon.CRM.models.Designations;
import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.repository.CompanyRepository;
import com.MotherSon.CRM.repository.DepartmentsRepository;
import com.MotherSon.CRM.repository.DesignationsRepository;
import com.MotherSon.CRM.repository.RoleRepository;
 
import jakarta.transaction.Transactional;
 
@Configuration
public class RoleInitializer {
     
	
	@Transactional
	@Bean
	CommandLineRunner createSuperAdminRole(RoleRepository roleRepository,CompanyRepository companyRepository,DepartmentsRepository departmentsrepository,DesignationsRepository designationrepository) {
	    return args -> {
	        System.out.println("CommandLineRunner is executing...");
	        
	        // Check if "Super Admin" role already exists in the database
	        Optional<Role> existingRole = roleRepository.findByRoleName("Super Admin");
	        if (!existingRole.isPresent()) {
	            Role superAdminRole = new Role();
	            superAdminRole.setRoleName("Super Admin");
	            superAdminRole.setStatus(true);
	            roleRepository.save(superAdminRole); // Save the role to the database
	            System.out.println("Super Admin role created successfully.");
	        } else {
	            System.out.println("Super Admin role already exists.");
	        }
	        Optional<Company> existingCompany = companyRepository.findByCompanyname("Motherson Travel");
            if (!existingCompany.isPresent()) {
                Company defaultCompany = new Company();
                defaultCompany.setCompanyname("Motherson Travel");
                defaultCompany.setCompanyaddress("Noida Sector - 126, Uttar Pradesh");
                defaultCompany.setCompanyemail("info@mothersontravel.com");
                defaultCompany.setCompanyphone("9311066483");
                defaultCompany.setCompanycountrycode("India (+91)");
                defaultCompany.setCompanywebsite("https://www.mothersontravel.com");
                defaultCompany.setCreatedby("Anshul");
                defaultCompany.setModifiedby("Anshul");
                defaultCompany.setParent_id((long) 0);
                defaultCompany.setIsdelete(false);
                defaultCompany.setStatus(true);
                
                defaultCompany.setIpaddress("123.76.987");
                // Add default logos to the list
                List<String> defaultLogos = List.of(
                        "https://example.com/logos/logo1.png",
                        "https://example.com/logos/logo2.png"
                );
                defaultCompany.setCompanylogo(defaultLogos);
                LocalDateTime now = LocalDateTime.now();
                defaultCompany.setCreateddate(now);
                defaultCompany.setModifieddate(now);
                
                companyRepository.save(defaultCompany); // Save the company to the database
                System.out.println("Company created successfully.");
            } else {
                System.out.println("Company already exists.");
            }
	        
                   Optional<Departments>existdepartment=departmentsrepository.findByDepartmentName("Admin");
                   
            if(!existdepartment.isPresent())
            {
            	Departments departmentex=new Departments();
            	
            	departmentex.setDepartmentName("Admin");
            	departmentex.setCreatedBy("Anshul");
            	departmentex.setModifiedBy("Anshul");
            	departmentex.setStatus(true);
            	departmentex.setIsdelete(false);
            	departmentex.setIpaddress("788.56.433");
            	LocalDateTime now=LocalDateTime.now();
            	departmentex.setCreatedDate(now);
            	departmentex.setModifiedDate(now);
            	departmentsrepository.save(departmentex);
            	System.out.println("Departments created successfully.");
            	
            }
            else
            {
            	System.out.println("Departments already exist");
            }
            
  
            Optional<Designations> existdesignation = designationrepository.findByDesignationName("Admin Head");
            if (!existdesignation.isPresent()) {
                // Fetch the department by ID
                Long departmentId = 1L; // Assuming '1' is the department ID you want to associate
                Optional<Departments> department = departmentsrepository.findById(departmentId);
 
                if (department.isPresent()) {
                    // Create and set Designation entity
                    Designations designationex = new Designations();
                    designationex.setDesignationName("Admin Head");
                    designationex.setCreatedBy("Anshul");
                    designationex.setModifiedBy("Anshul");
                    designationex.setIsdelete(false);
                    designationex.setStatus(true);
                    designationex.setIpAddress("435.54.87");
 
                    // Set current timestamps
                    LocalDateTime now = LocalDateTime.now();
                    designationex.setCreatedDate(now);
                    designationex.setModifiedDate(now);
 
                    // Set the associated department
                    designationex.setDepartments(department.get());
 
                    // Save the Designation
                    designationrepository.save(designationex);
                    System.out.println("Designation 'Admin Head' created successfully.");
                } else {
                    System.out.println("Department with ID " + departmentId + " not found. Designation creation aborted.");
                }
                
            }
            
            else {
                System.out.println("Designation 'Admin Head' already exists.");
            }
	    };
	}
}
 
 