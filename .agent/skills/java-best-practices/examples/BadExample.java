package com.empresa.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Exemplo de MÁ PRÁTICA em Java
 * NÃO SIGA ESTE PADRÃO!
 */

// ❌ RUIM: Classe com múltiplas responsabilidades
public class User {
    // ❌ Campos públicos (encapsulamento ruim)
    public String id;
    public String name;
    public String email;
    public String password;
    public boolean active;
    
    // ❌ Método que deveria estar em Repository
    public void saveToDatabase() {
        Database db = new Database();
        db.insert(this);
    }
    
    // ❌ Método que deveria estar em UserValidator
    public boolean validateEmail() {
        return email.contains("@");
    }
    
    // ❌ Método que deveria estar em EmailService
    public void sendWelcomeEmail() {
        try {
            EmailSender sender = new EmailSender();
            sender.send(email, "Bem-vindo!");
        } catch (Exception e) {
            // ❌ Engole a exceção silenciosamente!
        }
    }
    
    // ❌ Método que deveria estar em PasswordService
    public void changePassword(String newPassword) {
        this.password = newPassword; // ❌ Sem criptografia!
        saveToDatabase();
    }
    
    // ❌ Nome de método em snake_case (deve ser camelCase)
    public void process_user_data() {
        // lógica aqui
    }
}

// ❌ RUIM: UserService acoplado
public class UserService {
    
    // ❌ Sem Dependency Injection - acoplamento tight
    public void createUser(String name, String email) {
        // ❌ Cria dependências com 'new' em vez de injetar
        User user = new User();
        user.id = generateId();
        user.name = name;
        user.email = email;
        user.active = true;
        
        // ❌ Validação ruim
        if (email == null) {
            return; // silenciosamente não faz nada
        }
        
        // ❌ Múltiplas responsabilidades no mesmo método
        try {
            user.saveToDatabase();
        } catch (Exception e) {
            // ❌ printStackTrace é ruim!
            e.printStackTrace();
        }
        
        user.sendWelcomeEmail();
    }
    
    // ❌ Método que retorna null (perigoso!)
    public User getUserById(String id) {
        User user = null;
        try {
            Database db = new Database();
            user = db.query(id);
        } catch (Exception e) {
            // ❌ Retorna null silenciosamente
        }
        return user; // Pode causar NullPointerException!
    }
    
    // ❌ Método com muitos parâmetros (acima de 4)
    public void updateUser(String id, String name, String email, String password, 
                          boolean active, String phone, String address, String city) {
        // Código complexo com muitos parâmetros
    }
    
    // ❌ Método gigante (>50 linhas)
    public void processAllUsers() {
        Database db = new Database();
        List<User> users = db.queryAll();
        
        for (User user : users) {
            if (user.isActive()) {
                // ❌ Lógica manual em vez de Streams
                user.validateEmail();
                
                // ❌ Números mágicos sem constantes
                if (users.size() > 100) {
                    // processar
                }
                
                // ❌ Lógica complexa aqui
                try {
                    EmailSender sender = new EmailSender();
                    sender.send(user.email, "Nova mensagem");
                    user.saveToDatabase();
                } catch (Exception e) {
                    // ❌ Exception genérica
                }
            }
        }
    }
    
    // ❌ Método que retorna lista mutável (modificável)
    public List<User> getAllUsers() {
        Database db = new Database();
        return db.queryAll(); // Retorna lista que pode ser modificada externamente
    }
    
    private String generateId() {
        return "ID_" + Math.random(); // ❌ Não é determinístico
    }
}

// ❌ RUIM: Constante sem UPPER_SNAKE_CASE
public class UserConstants {
    public static final int maxRetries = 3; // ❌ Deve ser MAX_RETRIES
    public static final String default_email = "noreply@example.com"; // ❌ Deve ser DEFAULT_EMAIL
}

// ❌ RUIM: Classe mutável (não é segura para threads)
public class UserDTO {
    // ❌ Setters permitem modificação após criação
    private String id;
    private String name;
    
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public String getName() { return name; }
}

// ❌ RUIM: Validação ruim
public class UserValidator {
    
    // ❌ Sem validação de parâmetros
    public boolean validate(User user) {
        boolean valid = true;
        
        // ❌ Validação sem mensagens de erro
        if (user.email != null) {
            if (user.email.contains("@")) {
                valid = true;
            } else {
                valid = false;
            }
        }
        
        // ❌ Retorna boolean em vez de lançar exceção
        return valid;
    }
}

// ❌ RUIM: Repository sem interface
public class BadUserRepository {
    
    // ❌ Sem injeção de dependência
    private Database database = new Database();
    
    // ❌ Sem tratamento de exceção específica
    public User findById(String id) {
        try {
            return database.query(id);
        } catch (Exception e) {
            // ❌ Engole exceção
            return null;
        }
    }
    
    // ❌ Nome de método genérico
    public List<User> get() {
        return database.queryAll();
    }
}

// ❌ RUIM: Builder mútavel
public class UserBuilder {
    public String id;
    public String name;
    public String email;
    
    // ❌ Não retorna 'this' (não é fluent)
    public void setId(String id) {
        this.id = id;
    }
    
    public User build() {
        User user = new User();
        user.id = this.id;
        user.name = this.name;
        user.email = this.email;
        return user;
    }
}
