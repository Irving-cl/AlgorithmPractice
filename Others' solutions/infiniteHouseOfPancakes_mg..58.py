def func(n, a):
	ans = 1000
	for x in range(1, 1001):
		tmp = x
		for i in range(n):
			tmp = tmp + (a[i] - 1) / x
		ans = min(ans, tmp)
	return ans
	
T = int(raw_input())

for t in range(T):
	n = int(raw_input())
	a = map(int, raw_input().split(' '))
	print 'Case #' + str(t+1) + ': ' + str(func(n, a))
