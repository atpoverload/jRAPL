package jrapl;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/** Very simple energy monitor that reports energy consumption over 10 millisecond intervals. */
final class EnergyMonitor {
  // TODO: this is cumbersome, should really have an `isAvailable` method somewhere
  private static Supplier<EnergySample> getEnergySource() {
    if (!MicroArchitecture.NAME.equals(MicroArchitecture.UNKNOWN)
        && MicroArchitecture.SOCKETS > 0) {
      return Rapl::sample;
    } else if (Powercap.SOCKETS > 0) {
      return Powercap::sample;
    }
    throw new IllegalStateException("no energy source found!");
  }

  private static BiFunction<EnergySample, EnergySample, EnergyInterval> getEnergyDiffer() {
    if (!MicroArchitecture.NAME.equals(MicroArchitecture.UNKNOWN)
        && MicroArchitecture.SOCKETS > 0) {
      return Rapl::difference;
    } else if (Powercap.SOCKETS > 0) {
      return Powercap::difference;
    }
    throw new IllegalStateException("no energy source found!");
  }

  public static void main(String[] args) throws Exception {
    Supplier<EnergySample> source = getEnergySource();
    BiFunction<EnergySample, EnergySample, EnergyInterval> differ = getEnergyDiffer();

    EnergySample last = source.get();
    while (true) {
      Thread.sleep(10);
      EnergySample current = source.get();
      EnergyInterval interval = differ.apply(last, current);
      LoggerUtil.LOGGER.info(String.format("%s", interval));
      last = current;
    }
  }
}
