# 🏆 study-2025

---

## 📚 브랜치 전략

| 구분        | 규칙              |
|:----------|:----------------|
| 메인 브랜치    | `main`          |
| 과제 작업 브랜치 | `feature/{계정명}` |

### 예시 브랜치명

- `feature/mk.jang`

---

## 📤 PR(Pull Request) 작성 규칙

| 구분           | 규칙                              |
|:-------------|:--------------------------------|
| PR 대상        | 항상 `feature/xxx` → `main` 으로 PR |
| PR 제목        | `[week_XX] 계정명 PR`              |
| PR 작성시 자동 액션 | 문법 체크 + PR 자동 코멘트               |

---

## 🛠 GitHub Actions 자동화

| 기능      | 설명                                |
|:--------|:----------------------------------|
| 문법 체크   | Python, Java, JavaScript 문법 자동 검사 |
| PR 성공 시 | "✅ 코드 문법 검사를 통과했습니다" 코멘트 남김       |
| PR 실패 시 | "❌ 코드 문법 검사에 실패했습니다" 경고 코멘트 남김    |

### 문법 검사 기준

- Python: `python3 -m compileall -q .`
- Java: `javac`
- JavaScript: `node --check`


## 📁 프로젝트 폴더 구조

```plaintext
study-2025/
├── README.md
├── week_01/
│   ├── algorithm/
│   │   ├── mk.jang/
│   │   │   ├── Problem01.py
│   │   │   ├── ...
└── .github/
    ├── workflows/
    │   ├── code-check.yml
    │   ├── pr-comment.yml
    │   ├── ...
