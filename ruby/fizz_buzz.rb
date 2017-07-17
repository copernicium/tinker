a = 1
while a < 100
	a += 1
	if a % 5 == 0 && a % 7 == 0
		puts "Fizz Buzz"
	elsif a % 5 == 0
		puts "Fizz"
	elsif a % 7 == 0
		puts "Buzz"
	else
		puts a
	end
end
