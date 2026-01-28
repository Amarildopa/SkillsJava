#!/usr/bin/env python3
"""
Validador de código Java para guardrails de boas práticas
Verifica: naming conventions, SRP, tratamento de exceções, etc.
"""

import sys
import re
from pathlib import Path

class JavaValidator:
    def __init__(self, filepath):
        self.filepath = filepath
        self.errors = []
        self.warnings = []
        
    def validate_file(self):
        """Executa todas as validações"""
        try:
            with open(self.filepath, 'r', encoding='utf-8') as f:
                content = f.read()
                lines = content.split('\n')
        except FileNotFoundError:
            print(f"Erro: Arquivo '{self.filepath}' não encontrado.")
            sys.exit(1)
        
        self._check_naming_conventions(content, lines)
        self._check_exception_handling(content)
        self._check_class_size(lines)
        self._check_method_size(lines)
        self._check_magic_numbers(content)
        self._check_dependencies(content)
        
        return self._report_results()
    
    def _check_naming_conventions(self, content, lines):
        """Verifica convenções de nomenclatura"""
        # Classes devem ser PascalCase
        classes = re.findall(r'public\s+(?:class|interface|enum)\s+(\w+)', content)
        for cls in classes:
            if not cls[0].isupper():
                self.errors.append(f"ERRO: Classe '{cls}' deve usar PascalCase")
            if '_' in cls and not cls.isupper():  # mas permite ENUM_VALUE
                self.errors.append(f"ERRO: Nome de classe '{cls}' não deve conter underscores")
        
        # Métodos devem ser camelCase
        methods = re.findall(r'public\s+\w+\s+([a-z_]\w*)\s*\(', content)
        for method in methods:
            if method.startswith('_'):
                self.errors.append(f"ERRO: Método '{method}' não deve começar com underscore")
            if '_' in method and not method.isupper():
                self.warnings.append(f"AVISO: Método '{method}' deve usar camelCase, não snake_case")
        
        # Constantes devem ser UPPER_SNAKE_CASE
        constants = re.findall(r'(private\s+)?static\s+final\s+\w+\s+(\w+)', content)
        for _, const in constants:
            if const != const.upper():
                self.errors.append(f"ERRO: Constante '{const}' deve usar UPPER_SNAKE_CASE")
    
    def _check_exception_handling(self, content):
        """Verifica tratamento de exceções"""
        # Não capture Exception genérica
        if re.search(r'catch\s*\(\s*Exception\s+', content):
            self.errors.append("ERRO: Não capture 'Exception' genérica. Capture exceções específicas.")
        
        # Não use e.printStackTrace()
        if 'printStackTrace' in content:
            self.errors.append("ERRO: Não use 'printStackTrace()'. Use logger em vez disso.")
        
        # Verifique se há try sem catch
        try_blocks = len(re.findall(r'try\s*{', content))
        catch_blocks = len(re.findall(r'catch\s*\(', content))
        if try_blocks > catch_blocks:
            self.warnings.append(f"AVISO: {try_blocks - catch_blocks} try block(s) sem catch detectado(s)")
    
    def _check_class_size(self, lines):
        """Verifica tamanho de classes"""
        class_count = 0
        for i, line in enumerate(lines):
            if re.search(r'public\s+class\s+', line):
                # Conta linhas até fechar a classe
                open_braces = 0
                for j in range(i, len(lines)):
                    open_braces += lines[j].count('{')
                    open_braces -= lines[j].count('}')
                    if open_braces == 0 and j > i:
                        class_size = j - i
                        if class_size > 300:
                            self.warnings.append(f"AVISO: Classe no ficheiro está com {class_size} linhas. "
                                                f"Considere dividir em classes menores (SRP).")
                        break
    
    def _check_method_size(self, lines):
        """Verifica tamanho de métodos"""
        for i, line in enumerate(lines):
            if re.search(r'public\s+\w+\s+(\w+)\s*\(', line):
                method_match = re.search(r'(\w+)\s*\(', line)
                method_name = method_match.group(1) if method_match else "unknown"
                
                # Conta linhas do método
                open_braces = line.count('{') - line.count('}')
                method_lines = 0
                for j in range(i, min(i + 100, len(lines))):
                    open_braces += lines[j].count('{') - lines[j].count('}')
                    method_lines += 1
                    if open_braces == 0 and j > i:
                        break
                
                if method_lines > 50:
                    self.warnings.append(f"AVISO: Método '{method_name}' tem {method_lines} linhas. "
                                        f"Considere refatorar em métodos menores.")
    
    def _check_magic_numbers(self, content):
        """Verifica números mágicos"""
        # Simples heurística: números isolados em cálculos
        magic_numbers = re.findall(r'=\s*(\d+)\s*[+\-*/]|[+\-*/]\s*(\d+)\s*[;)]', content)
        if magic_numbers:
            self.warnings.append("AVISO: Detectados números mágicos. Use constantes em vez disso.")
    
    def _check_dependencies(self, content):
        """Verifica acoplamento de dependências"""
        # Não use 'new' para criar dependências
        if re.search(r'=\s*new\s+\w+Service\s*\(', content):
            self.errors.append("ERRO: Não crie dependências com 'new'. Use Dependency Injection (construtor).")
    
    def _report_results(self):
        """Relata resultados e retorna código de saída"""
        if self.errors:
            print("❌ VALIDAÇÃO FALHOU\n")
            print("ERROS:")
            for error in self.errors:
                print(f"  • {error}")
        
        if self.warnings:
            print("\n⚠️  AVISOS:")
            for warning in self.warnings:
                print(f"  • {warning}")
        
        if not self.errors and not self.warnings:
            print("✅ Código segue as boas práticas!")
            return 0
        
        return 1 if self.errors else 0

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Uso: python validate_java_code.py <arquivo.java>")
        sys.exit(1)
    
    validator = JavaValidator(sys.argv[1])
    exit_code = validator.validate_file()
    sys.exit(exit_code)
