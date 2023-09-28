class Weapon

  attr_accessor :power
  attr_accessor :uses

  def initialize(power, uses)
    @power = power
    @uses = uses
  end

  def attack
    if @uses>0
      @uses = @uses - 1
      @power
    else
      0
    end
  end

  def to_s
    puts "W["+@power+", "+@uses+"]"
  end
end

arma = Weapon.new(2,5)

puts arma.attack
puts arma.attack
puts arma.attack
puts arma.attack
puts arma.attack
puts arma.attack

