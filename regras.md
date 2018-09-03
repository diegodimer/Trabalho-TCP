# Trabalho-TCP
##~ ~ ~ ~ vamo la moçada ~ ~ ~ ~
eu não sei usar esse troço direito também então vamo tudo aprende junto

quando forem adicionar coisas criem um branch novo pra nao ter perigo de cagar o antigo, p isso:

## tutorial basicasso
```
git branch *nome* - cria branch
git checkout *nome* - vai pro branch
```
faz os arquivos que precisa

digita 
```
git add . pra adicionar tudo ou git add *arquivo* pra adicionar só um
```

```
git commit -m *mensagem do commit*
git push origin *nome do branch*
```
depois se ta tudo certo da merge com o master, vai pro master
```
git checkout master
git merge *branch q criou*	
```

terminou? 
```
git checkout *master*
git fetch --all
git merge origin/master
```
#não façam merda

#REGRAS
##sim ha regras
quando forem criar coisinhas novas criem **NOVOS BRANCHS** e depois de pronto e testado deem merge com o master
no master só vamos manter coisas **TESTADAS E APROVADAS**
