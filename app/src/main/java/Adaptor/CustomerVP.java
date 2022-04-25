package Adaptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Frgments.MyStuffFragment;
import Frgments.ShopFragment;

public class CustomerVP extends FragmentStateAdapter {
    public CustomerVP(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ShopFragment();

            case 1:
                return new MyStuffFragment();
        }
        return new ShopFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
