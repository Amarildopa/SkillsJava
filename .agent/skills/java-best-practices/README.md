# Java Best Practices Skill para Google Antigravity 🚀

Skill completa para guardrails automáticos de boas práticas em Java no Google Antigravity.

---

## 📦 O que está incluído

```
java-best-practices/
├── SKILL.md                    # Arquivo principal com 10 regras de boas práticas
├── scripts/
│   └── validate_java_code.py  # Validador automático (Python)
├── examples/
│   ├── GoodExample.java       # ✅ Código seguindo padrões
│   └── BadExample.java        # ❌ Código com anti-padrões
├── INSTALL.md                 # Guia passo-a-passo de instalação
├── CHECKLIST.md               # Checklist completo de validação (14 seções)
└── README.md                  # Este arquivo
```

---

## 🎯 10 Regras Principais

| # | Regra | Exemplo |
|---|-------|---------|
| 1️⃣ | Convenções de Nomenclatura | `UserService.getUserById()` |
| 2️⃣ | Single Responsibility Principle | Uma classe, uma responsabilidade |
| 3️⃣ | Imutabilidade | `public final class Address {}` |
| 4️⃣ | Tratamento de Exceções | Exceções específicas, nunca genéricas |
| 5️⃣ | Collections e Streams | `.filter().map().collect()` |
| 6️⃣ | Dependency Injection | Injetar via construtor, não usar `new` |
| 7️⃣ | Validação de Entrada | Validar parâmetros no início |
| 8️⃣ | Optional para Nullability | `Optional<User> findById()` |
| 9️⃣ | Logging Apropriado | SLF4J/Log4j, nunca `printStackTrace()` |
| 🔟 | Builder Pattern | Para classes com múltiplos parâmetros |

---

## ⚡ Quick Start (5 minutos)

### 1. Criar a pasta
```bash
mkdir -p .agent/skills/java-best-practices/scripts
mkdir -p .agent/skills/java-best-practices/examples
```

### 2. Copiar os 4 arquivos principais
- `SKILL.md` → `.agent/skills/java-best-practices/`
- `validate_java_code.py` → `.agent/skills/java-best-practices/scripts/`
- `GoodExample.java` → `.agent/skills/java-best-practices/examples/`
- `BadExample.java` → `.agent/skills/java-best-practices/examples/`

### 3. Usar no Antigravity
```
Você: "Create a UserService class to handle user operations"

Antigravity:
✅ Ativa java-best-practices
✅ Cria código seguindo todas as 10 regras
✅ Adiciona logging, exceções, validação
✅ Usa Dependency Injection
```

### 4. Validar código
```
Você: "@java-best-practices validate UserService.java"

Resultado:
✅ Convenções: OK
✅ SRP: OK
✅ Exceções: 1 aviso (catch genérica)
```

---

## 📖 Documentação Completa

### SKILL.md
Arquivo principal com:
- ✅ 10 regras de boas práticas detalhadas
- ✅ Exemplos de código (bom vs ruim) para cada regra
- ✅ Checklist de validação
- ✅ Padrões de uso

### INSTALL.md
Guia de instalação:
- ✅ Estrutura de pastas (workspace vs global)
- ✅ Passo-a-passo de instalação
- ✅ Testes de funcionamento
- ✅ Integração com CI/CD (GitHub Actions, Git hooks)
- ✅ Troubleshooting

### CHECKLIST.md
Checklist completo:
- ✅ 14 seções de validação
- ✅ Cada seção com exemplos ✅/❌
- ✅ Pronto para usar antes de commitar código
- ✅ Testing-friendly e segurança

### GoodExample.java
Exemplo completo de código correto:
- ✅ Model imutável (User)
- ✅ Repository com interface (IUserRepository)
- ✅ Service com lógica de negócio
- ✅ Builder Pattern
- ✅ Logging apropriado
- ✅ Tratamento de exceções
- ✅ Streams e Optional

### BadExample.java
Exemplo com anti-padrões:
- ❌ Múltiplas responsabilidades
- ❌ Campos públicos
- ❌ Exceções genéricas
- ❌ Acoplamento tight
- ❌ printStackTrace()
- ❌ Nomes ruins
- ❌ Validação fraca

---

## 🔧 Validação Automática

O script `validate_java_code.py` verifica:

```bash
python3 validate_java_code.py seu_arquivo.java
```

Checagens:
- ✅ Convenções de nomenclatura (PascalCase, camelCase, UPPER_SNAKE_CASE)
- ✅ Exceções genéricas (catch Exception)
- ✅ printStackTrace() usage
- ✅ Tamanho de classes (>300 linhas)
- ✅ Tamanho de métodos (>50 linhas)
- ✅ Números mágicos
- ✅ Acoplamento de dependências

**Saída**:
```
❌ VALIDAÇÃO FALHOU

ERROS:
  • ERRO: Classe 'user' deve usar PascalCase
  • ERRO: Não capture 'Exception' genérica

⚠️  AVISOS:
  • AVISO: Método tem 75 linhas
  • AVISO: Detectados números mágicos
```

---

## 🚀 Casos de Uso

### Caso 1: Criar novo código
```
Você: "Create a PaymentService class that handles payment processing"

Antigravity:
1. Ativa skill java-best-practices
2. Cria classe `PaymentService` (PascalCase ✅)
3. Injeta `IPaymentRepository` via construtor ✅
4. Usa `Optional<Payment>` para resultados ✅
5. Implementa logging com SLF4J ✅
6. Trata exceções específicas ✅
```

### Caso 2: Refatorar código existente
```
Você: "Refactor this UserService according to best practices"
[cola código]

Antigravity:
1. Executa validate_java_code.py
2. Identifica problemas:
   - classe muito grande
   - métodos muito longos
   - exceção genérica
   - sem logging
3. Fornece versão refatorada ✅
```

### Caso 3: Revisar pull request
```
Você: "Review this code for Java quality standards"

Antigravity:
1. Verifica SOLID principles
2. Checa patterns de design
3. Valida exceções e logging
4. Sugere melhorias
5. Fornece exemplos
```

---

## 📊 Integração com CI/CD

### GitHub Actions
```yaml
name: Java Quality Check
on: [push, pull_request]
jobs:
  validate:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - run: python3 .agent/skills/java-best-practices/scripts/validate_java_code.py src/main/java/**/*.java
```

### Git Pre-commit Hook
```bash
#!/bin/bash
for file in $(git diff --cached --name-only | grep ".java$"); do
    python3 .agent/skills/java-best-practices/scripts/validate_java_code.py "$file"
    [ $? -ne 0 ] && exit 1
done
```

---

## 🎓 Estrutura Recomendada de Projeto

```
src/main/java/com/empresa/user/
├── controller/
│   └── UserController.java
├── service/
│   └── UserService.java
├── repository/
│   ├── IUserRepository.java
│   └── UserRepository.java
├── model/
│   └── User.java
├── dto/
│   ├── CreateUserRequest.java
│   └── UserResponse.java
├── exception/
│   ├── UserException.java
│   └── UserNotFoundException.java
└── validator/
    └── UserValidator.java
```

Cada package tem responsabilidade única! ✅

---

## 🎯 Benefícios

| Benefício | Como |
|-----------|------|
| **Consistência** | Agente gera código idêntico em padrões |
| **Qualidade** | Menos bugs, código mais legível |
| **Velocidade** | Menos code reviews necessários |
| **Educação** | Time aprende padrões automaticamente |
| **Manutenibilidade** | Código fácil de entender e modificar |
| **Testabilidade** | Código desacoplado é fácil de testar |
| **Segurança** | Tratamento robusto de erros por padrão |

---

## 🔍 Exemplos de Transformação

### Antes (❌ Ruim)
```java
public class User {
    public void saveToDatabase() { /* ... */ }
    public void sendEmail() { /* ... */ }
    public boolean validateEmail() { /* ... */ }
}
```

### Depois (✅ Bom)
```java
public final class User { /* modelo */ }
public class UserRepository { /* persistência */ }
public class UserService { /* lógica */ }
public class EmailService { /* envio de email */ }
public class UserValidator { /* validação */ }
```

---

## 📚 Próximas Personalizações

1. **Adicione padrões da sua empresa**
   - Edite `SKILL.md` com regras específicas
   - Customize `validate_java_code.py` com suas validações

2. **Integre com Checkstyle**
   - Adicione `checkstyle-config.xml` em `resources/`
   - Configure rules customizados

3. **Use como template**
   - Skill funciona como base para outras linguagens
   - Reutilize estrutura para Python, Go, etc.

4. **Treine seu time**
   - Use `GoodExample.java` + `BadExample.java` como material de aprendizado
   - Execute `CHECKLIST.md` antes de commitar

---

## 🆘 Troubleshooting

### Problema: Skill não aparece no Antigravity
**Solução**: Verifique estrutura de pastas exata:
```bash
.agent/skills/java-best-practices/SKILL.md
```

### Problema: Script Python não executa
**Solução**:
```bash
chmod +x scripts/validate_java_code.py
python3 --version  # deve ser 3.6+
```

### Problema: Validador retorna falsos positivos
**Solução**: Ajuste regexes em `validate_java_code.py` conforme necessário

---

## 📞 Referência Rápida

| Comando | Função |
|---------|--------|
| `@java-best-practices activate` | Ativar skill |
| `@java-best-practices validate file.java` | Validar arquivo |
| `@java-best-practices refactor this code` | Refatorar |
| `@java-best-practices review naming` | Revisar padrão específico |

---

## 📈 Métricas de Sucesso

Após implementar a skill, você deve ver:

- ✅ 100% de classes seguindo PascalCase
- ✅ 0 `Exception` genéricas capturadas
- ✅ 0 `printStackTrace()` no código
- ✅ 100% de métodos públicos validando parâmetros
- ✅ 100% de métodos com logging apropriado
- ✅ 0 dependências criadas com `new`
- ✅ <300 linhas por classe
- ✅ <50 linhas por método

---

## 📄 Versão

- **Versão**: 1.0
- **Data**: Janeiro 2026
- **Mantido para**: Google Antigravity
- **Idioma**: Português (Brasil)
- **Status**: ✅ Pronto para produção

---

## 🎁 Bônus: Exemplos por Domínio

Você pode estender a skill com exemplos para:

- Payment Processing (PaymentService, PaymentRepository, TransactionValidator)
- User Management (UserService, UserRepository, PasswordService)
- E-commerce (ProductService, OrderService, InventoryService)
- Pet Services (seu caso! AgendamentoService, ClienteRepository, etc.)

---

## 🙌 Créditos

Skill criada para **Google Antigravity** como ferramenta de guardrails automáticos para qualidade de código Java.

Baseado em:
- ✅ Google Java Style Guide
- ✅ Effective Java (Joshua Bloch)
- ✅ Clean Code (Robert C. Martin)
- ✅ Design Patterns (Gang of Four)
- ✅ SOLID Principles

---

**Aproveite! Seu código Java agora é guiado por guardrails automáticos de boas práticas! 🚀**
