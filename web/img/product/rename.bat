@echo off
setlocal ENABLEDELAYEDEXPANSION
set count=1
for %%a in (*.jpg) do (
  ren "%%a" "!count!.jpg"
  set /a count+=1
)
endlocal
echo 所有 .jpg 文件已重命名。
pause