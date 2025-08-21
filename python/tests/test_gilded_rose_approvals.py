import io
import sys
import os

from approvaltests import verify

sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
from texttest_fixture import main

def test_gilded_rose_approvals():
    orig_sysout = sys.stdout
    try:
        fake_stdout = io.StringIO()
        sys.stdout = fake_stdout
        sys.argv = ["texttest_fixture.py", 30]
        main()
        answer = fake_stdout.getvalue()
    finally:
        sys.stdout = orig_sysout

    verify(answer)

if __name__ == "__main__":
    test_gilded_rose_approvals()
