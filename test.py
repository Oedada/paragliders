from __future__ import annotations

from dataclasses import dataclass

from vpython import arrow, label, rate, sphere, vector


@dataclass
class Vector3[T]:
    x: float
    y: float
    z: float

    @classmethod
    def from_vector3(cls, vec: Vector3) -> T:
        return cls(vec.x, vec.y, vec.z)

    def __init__(self, x: float = 0, y: float = 0, z: float = 0):
        self.x = x
        self.y = y
        self.z = z

    def __add__(self, other: T) -> T:
        return Vector3(self.x + other.x, self.y + other.y, self.z + other.z)

    def __mul__(self, s: float | int) -> Vector3:
        return Vector3(self.x * s, self.y * s, self.z * s)

    def __truediv__(self, s: float | int) -> Vector3:
        return Vector3(self.x / s, self.y / s, self.z / s)

    def __abs__(self) -> float:
        return (self.x**2+self.y**2+self.z**2)**0.5


class Position(Vector3):
    pass


class Velocity(Vector3):
    pass


class Acceleration(Vector3):
    pass


class Force(Vector3):
    pass


class Body:
    def __init__(
        self,
        mass_kilograms: float,
        start_forces: list[Force],
        start_velocity: Velocity = Velocity(0, 0, 0),
        delta_time_milliseconds: float = 50,
    ):
        self.forces: list[Force] = start_forces
        self.forces_dict: dict[str, Force] = {}
        self.velocity: Velocity = start_velocity
        self.dt = delta_time_milliseconds / 1000
        self.m = mass_kilograms

    def set_force(self, name: str, force: Force) -> None:
        self.forces_dict[name] = (force, len(self.forces))
        self.forces.append(force)

    def rm_force(self, name: str) -> None:
        self.forces.pop(self.forces[name][1])
        del self.forces[name]

    def update_velocity(self) -> None:
        print(self.velocity)
        speed = abs(self.velocity)
        drag = self.velocity * (
            -0.5 * Constants.p * Constants.S * Constants.CD * speed
        )
        self.set_force(name="upper", force=Force(y=0.5*Constants.p*Constants.S*Constants.CL*speed))
        self.set_force(name="drag", force=drag)
        a = sum([f / self.m for f in self.forces], Acceleration(0, 0, 0))
        self.velocity += a * self.dt


class Constants:
    g = -9.81
    ticks_per_second = 20
    dt = 1000 / ticks_per_second
    m = 80
    p = 1.225
    S = 25
    CL = 0.3
    CD = 0.15


body = Body(
    mass_kilograms=80,
    start_forces=[Force(y=Constants.g * Constants.m)],
    start_velocity=Velocity(x=1, z=1),
    delta_time_milliseconds=Constants.dt,
)

position: Position = Position(0, 0, 0)


def make_axis(direction, text, length=100):
    arrow(pos=vector(0, 0, 0), axis=direction * length, shaftwidth=0.2)

    label(pos=direction * (length + 2), text=text, box=False, opacity=0)


make_axis(vector(1, 0, 0), "X")
make_axis(vector(-1, 0, 0), "X")
make_axis(vector(0, 1, 0), "Y")
make_axis(vector(0, -1, 0), "Y")
make_axis(vector(0, 0, 1), "Z")
make_axis(vector(0, 0, -1), "Z")
ball = sphere(pos=vector(position.x, position.y, position.z), radius=5)

while True:
    rate(1000 / Constants.dt)

    body.update_velocity()

    position = position + body.velocity * Constants.dt / 1000

    ball.pos = vector(position.x, position.y, position.z)
