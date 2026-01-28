# 📖 Guia de Instalação - Skill de Java para Antigravity

## 🚀 Passo 1: Estrutura de Pastas

Crie a seguinte estrutura no seu projeto ou globalmente:

### Opção A: Workspace-Específico (Recomendado para começar)
```
seu-projeto/
└── .agent/
    └── skills/
        └── java-best-practices/
            ├── SKILL.md                      # ← arquivo principal
            ├── scripts/
            │   ├── validate_java_code.py
            │   ├── check_naming_conventions.py
            │   └── analyze_design_patterns.py
            ├── examples/
            │   ├── GoodExample.java
            │   └── BadExample.java
            ├── references/
            │   └── java-standards.md
            └── resources/
                ├── checkstyle-config.xml
                └── code-review-checklist.md
```

### Opção B: Global (Para todos seus projetos)
```
~/.gemini/antigravity/
└── skills/
    └── java-best-practices/
        └── [mesma estrutura acima]
```

---

## 📋 Passo 2: Copiar Arquivos

### Arquivo 1: SKILL.md (Principal)
Este é o arquivo que define toda a skill. Salve exatamente como está.

**Local**: `.agent/skills/java-best-practices/SKILL.md`

---

### Arquivo 2: validate_java_code.py
Script Python para validação automática de código.

**Local**: `.agent/skills/java-best-practices/scripts/validate_java_code.py`

**Permissões**: Torne executável:
```bash
chmod +x .agent/skills/java-best-practices/scripts/validate_java_code.py
```

---

### Arquivo 3: GoodExample.java
Exemplo de código seguindo todas as boas práticas.

**Local**: `.agent/skills/java-best-practices/examples/GoodExample.java`

---

### Arquivo 4: BadExample.java
Exemplo de código com anti-padrões (para comparação).

**Local**: `.agent/skills/java-best-practices/examples/BadExample.java`

---

## ⚙️ Passo 3: Configuração do Antigravity

### 3.1 Verificar Instalação
```bash
# No seu projeto, teste se a skill está carregada
cd seu-projeto
antigravity list-skills
```

Você deve ver:
```
Available Skills:
  ✓ java-best-practices (v1.0)
```

### 3.2 Habilitar a Skill
No Antigravity, mencione explicitamente:
```
@java-best-practices activate
```

---

## 🧪 Passo 4: Testes de Funcionamento

### Teste 1: Criar uma classe Java
```
Você: "Create a PaymentService class to handle payment processing"

Antigravity deve:
✅ Ativar a skill java-best-practices
✅ Criar classe com PascalCase
✅ Usar Dependency Injection
✅ Adicionar logging com SLF4J
✅ Tratar exceções apropriadamente
```

### Teste 2: Validar código existente
```
Você: "@java-best-practices validate UserService.java"

Antigravity deve:
✅ Executar validate_java_code.py
✅ Relatar erros e avisos
✅ Sugerir correções
```

### Teste 3: Revisão de pull request
```
Você: "Review this code for Java best practices"
[cola o código]

Antigravity deve:
✅ Executar análise completa
✅ Verificar SOLID principles
✅ Propor refatorações
✅ Fornecer exemplos corretos
```

---

## 🛠️ Passo 5: Customizações Opcionais

### 5.1 Adicione seu próprio padrão
Edite `SKILL.md` e adicione uma seção 11:

```markdown
### 11. **[Seu Padrão Personalizado]**

Descrição...

✅ **BOM**:
```java
// seu exemplo aqui
```

❌ **RUIM**:
```java
// seu contra-exemplo
```
```

### 5.2 Integre com Checkstyle
Adicione arquivo `.agent/skills/java-best-practices/resources/checkstyle-config.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE module PUBLIC "-//Puppy Crawl//DTD Check Configuration 1.3//EN" "http://www.puppycrawl.com/dtds/configuration_1_3.dtd">
<module name="Checker">
    <module name="TreeWalker">
        <module name="TypeName"/>
        <module name="MethodName"/>
        <module name="ConstantName"/>
    </module>
</module>
```

### 5.3 Configure para seu projeto
Se seu projeto tem padrões específicos, crie `java-standards.md`:

```markdown
# Padrões Específicos da [Sua Empresa]

## Convenções Adicionais

- Pacotes devem usar domínio reverso: `br.seudominio.projeto`
- Exceções customizadas devem ter sufixo `Exception`
- DTOs devem ter sufixo `Dto`
- ...
```

---

## 📊 Passo 6: Integração com CI/CD

### 6.1 GitHub Actions
Crie `.github/workflows/java-quality.yml`:

```yaml
name: Java Code Quality

on: [push, pull_request]

jobs:
  quality:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      
      - name: Run Java Validator
        run: |
          python3 .agent/skills/java-best-practices/scripts/validate_java_code.py src/main/java/**/*.java
```

### 6.2 Git Hook (Pre-commit)
Crie `.git/hooks/pre-commit`:

```bash
#!/bin/bash

echo "🔍 Validando código Java..."

for file in $(git diff --cached --name-only --diff-filter=ACM | grep ".java$"); do
    python3 .agent/skills/java-best-practices/scripts/validate_java_code.py "$file"
    if [ $? -ne 0 ]; then
        echo "❌ Validação falhou para $file"
        exit 1
    fi
done

echo "✅ Código validado com sucesso"
```

Torne executável:
```bash
chmod +x .git/hooks/pre-commit
```

---

## 🎯 Passo 7: Referência Rápida

### Ativar Skill
```
@java-best-practices activate
```

### Validar Arquivo
```
@java-best-practices validate MyClass.java
```

### Refatorar Código
```
@java-best-practices refactor this code
[seu código aqui]
```

### Revisar Padrão Específico
```
@java-best-practices review naming conventions
```

---

## 🐛 Troubleshooting

### Problema: Skill não aparece
**Solução**: Verifique a estrutura de pastas. Deve estar em `.agent/skills/java-best-practices/` exatamente.

### Problema: Script Python não executa
**Solução**: 
```bash
chmod +x .agent/skills/java-best-practices/scripts/validate_java_code.py
python3 --version  # certifique que Python 3.6+ está instalado
```

### Problema: Antigravity não reconhece a skill
**Solução**: Reinicie o Antigravity ou faça `@refresh-skills`

### Problema: Validação retorna falsos positivos
**Solução**: Edite `validate_java_code.py` e ajuste as regexes

---

## 📚 Próximos Passos

1. ✅ Instale a skill (que você fez agora!)
2. 🧪 Teste com seu primeiro arquivo Java
3. 📈 Customize com padrões da sua empresa
4. 🔗 Integre com seu CI/CD
5. 📚 Treine seu time com os exemplos

---

## 📞 Suporte

Se encontrar problemas:

1. Verifique o arquivo `SKILL.md` está bem formatado
2. Confirme Python 3.6+ está instalado
3. Teste o script manualmente: `python3 validate_java_code.py seu_arquivo.java`
4. Consulte a seção Troubleshooting acima

---

**Versão**: 1.0  
**Última atualização**: Janeiro 2026  
**Status**: Pronto para uso ✅
